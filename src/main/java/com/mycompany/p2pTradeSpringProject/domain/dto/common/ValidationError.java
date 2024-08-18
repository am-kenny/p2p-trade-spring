package com.mycompany.p2pTradeSpringProject.domain.dto.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValidationError {
    String code;
    String message;
    String path;
}