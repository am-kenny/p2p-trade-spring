package com.mycompany.p2pTradeSpringProject.domain.dto.auth.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class RegistrationResponse {
    boolean success;
    Integer userId;
    Set<ValidationError> errors;
}
