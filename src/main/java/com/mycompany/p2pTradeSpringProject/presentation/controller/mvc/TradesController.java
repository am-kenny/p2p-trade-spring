package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.request.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.request.GetOpenTradesRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.response.CreateTradeResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.response.GetOpenTradesResponse;
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

        return "trade/tradeDetails";
    }

    @GetMapping()
    public String viewOpenTrades(GetOpenTradesRequest tradesRequest,
                                 Model model) {

        GetOpenTradesResponse getOpenTradesResponse = tradeService.getOpenTrades(tradesRequest);
        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("trades", getOpenTradesResponse.getOpenTrades());
        model.addAttribute("currencies", currencies);
        return "trade/tradeList";
    }


    @GetMapping("/create")
    public String createTrade(Model model,
                              @ModelAttribute("errors") ArrayList<Error> errors) {

        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currencies);

        return "trade/createTrade";
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
