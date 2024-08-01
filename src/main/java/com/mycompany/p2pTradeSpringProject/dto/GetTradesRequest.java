package com.mycompany.p2pTradeSpringProject.dto;

import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class GetTradesRequest {
    private Boolean buy;
    private Currency tradeCurrency;
    private Currency exchangeCurrency; //TODO: remake to dto
}
