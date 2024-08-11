package com.mycompany.p2pTradeSpringProject.domain.dto.trade;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.TradeUserDto;
import com.mycompany.p2pTradeSpringProject.domain.entity.Trade;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * DTO for {@link Trade}
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