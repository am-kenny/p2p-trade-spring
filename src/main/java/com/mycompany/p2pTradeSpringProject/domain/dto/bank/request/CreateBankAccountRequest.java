package com.mycompany.p2pTradeSpringProject.domain.dto.bank.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateBankAccountRequest {

    @NotNull
    String accountNumber;

    @NotNull
    Integer bankId;

    @NotNull
    Integer currencyId;

    @NotBlank
    String cardholderName;

}
