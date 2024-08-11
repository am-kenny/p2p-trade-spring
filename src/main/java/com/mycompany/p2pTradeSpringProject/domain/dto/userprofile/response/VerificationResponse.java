package com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class VerificationResponse {
    boolean success;
    Integer verificationId;
    Set<Error> errors;
}
