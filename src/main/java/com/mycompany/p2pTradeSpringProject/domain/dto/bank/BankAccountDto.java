package com.mycompany.p2pTradeSpringProject.domain.dto.bank;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;
import lombok.Builder;
import lombok.Value;


/**
 * DTO for {@link BankAccount}
 */
@Value
@Builder
public class BankAccountDto {
    Integer id;
    String accountNumber;
    BankDto bank;
    CurrencyDto currency;
    String cardholderName;
}