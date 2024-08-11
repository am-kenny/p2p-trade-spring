package com.mycompany.p2pTradeSpringProject.domain.dto.auth.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class RegistrationResponse {
    boolean success;
    Integer userId;
    Set<Error> errors;
}
