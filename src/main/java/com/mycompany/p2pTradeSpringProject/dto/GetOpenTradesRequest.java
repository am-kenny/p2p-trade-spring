package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class GetOpenTradesRequest {
    private Boolean buy;
    private CurrencyDto tradeCurrency;
    private CurrencyDto exchangeCurrency;
}
