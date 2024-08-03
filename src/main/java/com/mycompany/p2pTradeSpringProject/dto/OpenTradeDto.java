package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * DTO for {@link com.mycompany.p2pTradeSpringProject.persistence.entities.Trade}
 */
@Value
@Builder
public class OpenTradeDto {
    Integer id;
    TradeUserDto initiatorUser;
    Boolean isSeller;
    CurrencyDto tradeCurrency;
    Float tradeCurrencyAmount;
    CurrencyDto exchangeCurrency;
    Float exchangeRate;
    Instant createdAt;
}