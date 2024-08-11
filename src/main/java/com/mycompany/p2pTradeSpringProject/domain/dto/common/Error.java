package com.mycompany.p2pTradeSpringProject.domain.dto.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Error {
    String message;
}
