package com.mycompany.p2pTradeSpringProject.domain.dto.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    Integer status;
    String error;
    String message;
    String path;
}
