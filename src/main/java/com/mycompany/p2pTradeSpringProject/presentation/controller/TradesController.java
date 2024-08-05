package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.dto.*;
import com.mycompany.p2pTradeSpringProject.dto.Error;
import com.mycompany.p2pTradeSpringProject.service.CurrencyService;
import com.mycompany.p2pTradeSpringProject.service.TradeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
    public String getOpenTrade(@PathVariable int id,
                               HttpServletRequest request,
                               Model model) {
        if (request.getSession().getAttribute(
                "authenticatedUser") == null) {
            return "redirect:" + Urls.LOGIN; // Redirect to home page if user is already authenticated
        }

        tradeService.getOpenTradeById(id);
        model.addAttribute("trade", tradeService.getOpenTradeById(id));

        return "trade";
    }

    @GetMapping()
    public String getOpenTrades(GetOpenTradesRequest tradesRequest,
                                HttpServletRequest request,
                                Model model) {

        if (request.getSession().getAttribute("authenticatedUser") == null) {
            return "redirect:" + Urls.LOGIN; // Redirect to home page if user is already authenticated
        }



        GetOpenTradesResponse getOpenTradesResponse = tradeService.getOpenTrades(tradesRequest);
        model.addAttribute("trades", getOpenTradesResponse.getOpenTrades());
        model.addAttribute("currencies", getOpenTradesResponse.getCurrencies());
        return "trades";
    }


    @GetMapping("/create")
    public String createTrade(HttpServletRequest request,
                              Model model,
                              @ModelAttribute("errors") ArrayList<Error> errors) {
        if (request.getSession().getAttribute("authenticatedUser") == null) {
            return "redirect:" + Urls.LOGIN; // Redirect to home page if user is already authenticated
        }

        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currencies);

        return "createTrade";
    }

    @PostMapping()
    public String createTrade(CreateTradeRequest createTradeRequest,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {
        if (request.getSession().getAttribute("authenticatedUser") == null) {
            return "redirect:" + Urls.LOGIN; // Redirect to home page if user is already authenticated
        }

        CreateTradeResponse response = tradeService.createTrade(createTradeRequest); //TODO: add exception handling

        if (!response.isSuccess()) {
            redirectAttributes.addFlashAttribute("errors", response.getErrors());
            return "redirect:" + Urls.TRADES + "/create";
        }

        return "redirect:" + Urls.TRADES + "/" + response.getTradeId();
    }


}
