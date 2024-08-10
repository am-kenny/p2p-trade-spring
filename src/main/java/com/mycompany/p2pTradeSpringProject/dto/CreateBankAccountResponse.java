package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateBankAccountResponse {
    boolean success;
    Integer bankAccountId;
    List<Error> errors;
}
