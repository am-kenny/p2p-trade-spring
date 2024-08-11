package com.mycompany.p2pTradeSpringProject.domain.dto.trade.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateTradeResponse {
    boolean success;
    Integer tradeId;
    List<Error> errors;
}
