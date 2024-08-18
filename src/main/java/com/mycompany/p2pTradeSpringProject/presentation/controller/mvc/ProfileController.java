package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.BankAccountRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
import com.mycompany.p2pTradeSpringProject.security.CustomUserDetails;
import com.mycompany.p2pTradeSpringProject.service.BankAccountService;
import com.mycompany.p2pTradeSpringProject.service.BankService;
import com.mycompany.p2pTradeSpringProject.service.CurrencyService;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(Urls.PROFILE)
@AllArgsConstructor
public class ProfileController {

    private final UserVerificationService userVerificationService;
    private final BankAccountService bankAccountService;
    private final BankService bankService;
    private final CurrencyService currencyService;

    @GetMapping
    public String profile(@AuthenticationPrincipal CustomUserDetails userDetails,
                          Model model) {

        User user = userDetails.getUser();

        model.addAttribute("authenticatedUser", user);
        model.addAttribute("isVerified", userVerificationService.isVerified(user.getId()));
        return "user/profile";

    }

    @GetMapping("/bank_accounts")
    public String viewUsersBankAccounts(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        Model model) {

        User user = userDetails.getUser();
        List<BankAccountDto> bankAccounts = bankAccountService.getUsersBankAccounts(user.getId());

        model.addAttribute("bankAccounts", bankAccounts);
        return "user/bankAccounts";
    }

    @GetMapping("/bank_accounts/add")
    public String addBankAccount(Model model,
                                 @ModelAttribute("errors") ArrayList<ValidationError> errors) {

        model.addAttribute("banks", bankService.getAllBanks().getBanks());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "user/addBankAccount";
    }

    @PostMapping("/bank_accounts")
    public String addBankAccount(BankAccountRequest request,
                                 @AuthenticationPrincipal CustomUserDetails userDetails,
                                 RedirectAttributes redirectAttributes) {
        try {
            Integer bankId = bankAccountService.createBankAccountForUser(request, userDetails.getUser().getId());
            return "redirect:/profile/bank_accounts/" + bankId;
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("errors", e.getValidationErrors());
            return "redirect:/profile/bank_accounts/add";
        }

    }

    @GetMapping("/bank_accounts/{id}")
    public String viewBankAccount(@PathVariable int id,
                                  @AuthenticationPrincipal CustomUserDetails userDetails,
                                  Model model,
                                  @ModelAttribute("errors") ArrayList<ValidationError> errors) {

        BankAccountDto bankAccountDto = bankAccountService.getBankAccountById(id, userDetails.getUser().getId());
        model.addAttribute("bankAccount", bankAccountDto);
        model.addAttribute("banks", bankService.getAllBanks().getBanks());
        model.addAttribute("currencies", currencyService.getAllCurrencies());

        return "user/bankAccountDetails";
    }

    @PostMapping("/bank_accounts/{id}/edit")
    public String editBankAccount(@PathVariable int id,
                                  @AuthenticationPrincipal CustomUserDetails userDetails,
                                  BankAccountRequest request,
                                  RedirectAttributes redirectAttributes) {
        try {
            Integer bankAccountId = bankAccountService.editBankAccountForUser(request, userDetails.getUser().getId(), id);
            return "redirect:/profile/bank_accounts/" + bankAccountId;
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("errors", e.getValidationErrors());
            return "redirect:/profile/bank_accounts/" + id + "?edit";
        }
    }

    @PostMapping("/bank_accounts/{id}/delete")
    public String deleteBankAccount(@PathVariable int id,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {

        bankAccountService.deleteBankAccountForUser(userDetails.getUser().getId(), id);
        return "redirect:/profile/bank_accounts";
    }

}
