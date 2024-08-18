package com.mycompany.p2pTradeSpringProject.domain.dto.trade.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class CreateTradeResponse {
    boolean success;
    Integer tradeId;
    Set<ValidationError> errors;
}
