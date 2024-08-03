package com.mycompany.p2pTradeSpringProject.utils;

import com.mycompany.p2pTradeSpringProject.dto.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.dto.OpenTradeDto;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;

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
                .initiatorUser(UserMapper.toEntity(request.getUserId()))
                .isSeller(request.getIsSeller())
                .tradeCurrency(CurrencyMapper.toEntity(request.getTradeCurrencyId()))
                .tradeCurrencyAmount(request.getAmount())
                .exchangeCurrency(CurrencyMapper.toEntity(request.getExchangeCurrencyId()))
                .exchangeRate(request.getExchangeRate())
                .build();
    }
}
