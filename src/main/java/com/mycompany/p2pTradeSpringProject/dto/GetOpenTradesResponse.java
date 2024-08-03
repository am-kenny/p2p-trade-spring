package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetOpenTradesResponse {
    List<OpenTradeDto> openTrades;
    List<Error> errors;
}
