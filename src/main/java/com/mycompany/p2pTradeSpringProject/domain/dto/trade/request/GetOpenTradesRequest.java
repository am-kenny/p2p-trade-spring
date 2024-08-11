package com.mycompany.p2pTradeSpringProject.domain.dto.trade.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetOpenTradesRequest {
    Boolean buy;
    Integer tradeCurrencyId;
    Integer exchangeCurrencyId;
}
