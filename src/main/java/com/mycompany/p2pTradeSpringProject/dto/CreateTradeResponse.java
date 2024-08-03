package com.mycompany.p2pTradeSpringProject.dto;

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
