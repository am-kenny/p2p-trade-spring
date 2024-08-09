package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.dto.*;
import com.mycompany.p2pTradeSpringProject.dto.Error;
import com.mycompany.p2pTradeSpringProject.security.CustomUserDetails;
import com.mycompany.p2pTradeSpringProject.service.CurrencyService;
import com.mycompany.p2pTradeSpringProject.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(Urls.TRADES)
@AllArgsConstructor
public class TradesController {

    private final TradeService tradeService;
    private final CurrencyService currencyService;

    @GetMapping("/{id}")
    public String viewOpenTrade(@PathVariable int id,
                               Model model) {

        model.addAttribute("trade", tradeService.getOpenTradeById(id));

        return "trade";
    }

    @GetMapping()
    public String viewOpenTrades(GetOpenTradesRequest tradesRequest,
                                Model model) {

        GetOpenTradesResponse getOpenTradesResponse = tradeService.getOpenTrades(tradesRequest);
        model.addAttribute("trades", getOpenTradesResponse.getOpenTrades());
        model.addAttribute("currencies", getOpenTradesResponse.getCurrencies());
        return "trades";
    }


    @GetMapping("/create")
    public String createTrade(Model model,
                              @ModelAttribute("errors") ArrayList<Error> errors) {

        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currencies);

        return "createTrade";
    }

    @PostMapping()
    public String createTrade(CreateTradeRequest createTradeRequest,
                              @AuthenticationPrincipal CustomUserDetails userDetails,
                              RedirectAttributes redirectAttributes) {

        CreateTradeResponse response = tradeService.createTrade(createTradeRequest, userDetails.getUser().getId());

        if (!response.isSuccess()) {
            redirectAttributes.addFlashAttribute("errors", response.getErrors());
            return "redirect:" + Urls.TRADES + "/create";
        }

        return "redirect:" + Urls.TRADES + "/" + response.getTradeId();
    }


}
