package com.mycompany.p2pTradeSpringProject.domain.dto.trade.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.OpenTradeDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GetOpenTradesResponse {
    List<OpenTradeDto> openTrades;
    List<CurrencyDto> currencies;
    List<Error> errors;
}