package com.mycompany.p2pTradeSpringProject.presentation.controller.rest;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.OpenTradeDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.request.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.response.CreateTradeResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.response.GetOpenTradesResponse;
import com.mycompany.p2pTradeSpringProject.security.CustomUserDetails;
import com.mycompany.p2pTradeSpringProject.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api" + Urls.TRADES)
@AllArgsConstructor
public class RestTradesController {

    TradeService tradeService;

    @GetMapping
    public ResponseEntity<GetOpenTradesResponse> getOpenTrades(@RequestParam(required=false) Map<String,String> qparams) {
        return ResponseEntity.ok(tradeService.getOpenTrades(qparams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpenTradeDto> getOpenTradeById(@PathVariable int id) {
        return ResponseEntity.ok(tradeService.getOpenTradeById(id));
    }

    @PostMapping()
    public ResponseEntity<CreateTradeResponse> createTrade(@RequestBody CreateTradeRequest request,
                                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tradeService.createTrade(request, userDetails.getUser().getId()));
    }

}
