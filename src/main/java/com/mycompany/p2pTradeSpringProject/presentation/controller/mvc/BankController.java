package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
import com.mycompany.p2pTradeSpringProject.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping(Urls.BANKS)
@AllArgsConstructor
public class BankController {

    BankService bankService;

    @GetMapping("/create")
    public String addBank(@ModelAttribute("errors") ArrayList<ValidationError> errors) {
        return "bank/addBank";
    }

    @PostMapping
    public String createBank(CreateBankRequest request,
                             RedirectAttributes redirectAttributes) {
        try {
            Integer bankId = bankService.createBank(request);
            return "redirect:" + Urls.BANKS + "/" + bankId;
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("errors", e.getValidationErrors());
            return "redirect:" + Urls.BANKS + "/create";
        }

    }

    @GetMapping("/{id}")
    public String viewBankById(@PathVariable Integer id,
                               Model model) {

        BankDto bank = bankService.getBankById(id);
        model.addAttribute("bank", bank);

        return "bank/bankDetails";
    }
}
