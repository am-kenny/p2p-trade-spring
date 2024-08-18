package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.OpenTradeDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.request.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
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
import java.util.Map;

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
    public String viewOpenTrades(@RequestParam(required=false) Map<String,String> qparams,
                                 Model model) {

        List<OpenTradeDto> openTrades = tradeService.getOpenTrades(qparams);
        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("trades", openTrades);
        model.addAttribute("currencies", currencies);
        return "trade/tradeList";
    }


    @GetMapping("/create")
    public String createTrade(Model model,
                              @ModelAttribute("errors") ArrayList<ValidationError> errors) {

        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currencies);

        return "trade/createTrade";
    }

    @PostMapping()
    public String createTrade(CreateTradeRequest createTradeRequest,
                              @AuthenticationPrincipal CustomUserDetails userDetails,
                              RedirectAttributes redirectAttributes) {

        try {
            Integer tradeId = tradeService.createTrade(createTradeRequest, userDetails.getUser().getId());
            return "redirect:" + Urls.TRADES + "/" + tradeId;
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("errors", e.getValidationErrors());
            return "redirect:" + Urls.TRADES + "/create";
        }
    }

}
