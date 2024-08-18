package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.CreateBankResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
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

        CreateBankResponse response = bankService.createBank(request);

        if (!response.isSuccess()) {
            redirectAttributes.addFlashAttribute("errors", response.getErrors());
            return "redirect:" + Urls.BANKS + "/create";
        }

        return "redirect:" + Urls.BANKS + "/" + response.getBankId();
    }

    @GetMapping("/{id}")
    public String viewBankById(@PathVariable Integer id,
                               Model model) {

        BankDto bank = bankService.getBankById(id);
        model.addAttribute("bank", bank);

        return "bank/bankDetails";
    }
}
