package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

/**
 * DTO for {@link com.mycompany.p2pTradeSpringProject.persistence.entities.User}
 */
@Value
@Builder
public class TradeUserDto {
    Integer id;
    String username;
    Set<BankAccountDto> bankAccounts;
}