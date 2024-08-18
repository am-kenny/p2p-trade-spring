package com.mycompany.p2pTradeSpringProject.domain.dto.bank.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class BankAccountResponse {
    boolean success;
    Integer bankAccountId;
    Set<ValidationError> errors;
}
