package com.mycompany.p2pTradeSpringProject.domain.dto.bank.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BankAccountRequest {

    @NotBlank(message = "Account number is required")
    String accountNumber;

    @NotNull(message = "Bank id is required")
    Integer bankId;

    @NotNull(message = "Currency id is required")
    Integer currencyId;

    @NotBlank(message = "Cardholder name is required")
    String cardholderName;

}
