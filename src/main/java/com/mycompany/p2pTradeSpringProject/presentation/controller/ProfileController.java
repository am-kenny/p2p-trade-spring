package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankAccountRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.CreateBankAccountResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.GetBankAccountsResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
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
        GetBankAccountsResponse response = bankAccountService.getUsersBankAccounts(user.getId());

        model.addAttribute("bankAccounts", response.getBankAccounts());
        return "user/bankAccounts";
    }

    @GetMapping("/bank_accounts/add")
    public String addBankAccount(Model model,
                                 @ModelAttribute("errors") ArrayList<Error> errors) {

        model.addAttribute("banks", bankService.getAllBanks().getBanks());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "user/addBankAccount";
    }

    @PostMapping("/bank_accounts")
    public String addBankAccount(CreateBankAccountRequest request,
                                 @AuthenticationPrincipal CustomUserDetails userDetails,
                                 RedirectAttributes redirectAttributes) {

        CreateBankAccountResponse response = bankAccountService.createBankAccountForUser(request, userDetails.getUser().getId());

        if (!response.isSuccess()) {
            redirectAttributes.addFlashAttribute("errors", response.getErrors());
            return "redirect:/profile/bank_accounts/add";
        }

        return "redirect:/profile/bank_accounts";
    }

    @GetMapping("/bank_accounts/{id}")
    public String viewBankAccount(@PathVariable int id,
                                  @AuthenticationPrincipal CustomUserDetails userDetails,
                                  Model model) {

        BankAccountDto bankAccountDto = bankAccountService.getBankAccountById(id, userDetails.getUser().getId());
        model.addAttribute("bankAccount", bankAccountDto);

        return "user/bankAccountDetails";
    }

    @GetMapping("/bank_accounts/{id}/edit") //TODO: Implement
    public String editBankAccount(@PathVariable int id,
                                  @AuthenticationPrincipal CustomUserDetails userDetails,
                                  Model model) {

        User user = userDetails.getUser();


        return "user/editBankAccount";
    }

    @GetMapping("/bank_accounts/{id}/delete") //TODO: Implement
    public String deleteBankAccount(@PathVariable int id,
                                    @AuthenticationPrincipal CustomUserDetails userDetails,
                                    Model model) {

        User user = userDetails.getUser();


        return "user/deleteBankAccount";
    }

}
