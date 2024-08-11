package com.mycompany.p2pTradeSpringProject.service.mapper;

import com.mycompany.p2pTradeSpringProject.domain.dto.trade.request.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.OpenTradeDto;
import com.mycompany.p2pTradeSpringProject.domain.entity.Trade;

public class TradeMapper {
    public static OpenTradeDto mapToOpenTradeDto(Trade trade) {
        return OpenTradeDto.builder()
                .id(trade.getId())
                .initiatorUser(UserMapper.toTradeUserDto(trade.getInitiatorUser()))
                .isSeller(trade.getIsSeller())
                .tradeCurrency(CurrencyMapper.toDto(trade.getTradeCurrency()))
                .tradeCurrencyAmount(trade.getTradeCurrencyAmount())
                .exchangeCurrency(CurrencyMapper.toDto(trade.getExchangeCurrency()))
                .exchangeRate(trade.getExchangeRate())
                .createdAt(trade.getCreatedAt())
                .build();
    }

    public static Trade toEntity(CreateTradeRequest request) {
        return Trade.builder()
                .isSeller(request.getIsSeller())
                .tradeCurrency(CurrencyMapper.toEntity(request.getTradeCurrencyId()))
                .tradeCurrencyAmount(request.getAmount())
                .exchangeCurrency(CurrencyMapper.toEntity(request.getExchangeCurrencyId()))
                .exchangeRate(request.getExchangeRate())
                .build();
    }
}
