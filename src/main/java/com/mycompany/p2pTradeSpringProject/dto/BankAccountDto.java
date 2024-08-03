package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;


/**
 * DTO for {@link com.mycompany.p2pTradeSpringProject.persistence.entities.BankAccount}
 */
@Value
@Builder
public class BankAccountDto {
    Integer id;
    Integer cardNumber;
    BankDto bank;
    CurrencyDto currency;
    String cardholderName;
}