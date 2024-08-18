package com.mycompany.p2pTradeSpringProject.domain.dto.trade.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.trade.OpenTradeDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GetOpenTradesResponse {
    List<OpenTradeDto> openTrades;
}
