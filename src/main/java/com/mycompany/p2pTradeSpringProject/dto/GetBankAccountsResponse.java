package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetBankAccountsResponse {
    private List<BankAccountDto> bankAccounts;
    private List<Error> errors;
}
