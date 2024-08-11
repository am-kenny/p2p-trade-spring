package com.mycompany.p2pTradeSpringProject.domain.dto.userprofile;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

/**
 * DTO for {@link User}
 */
@Value
@Builder
public class TradeUserDto {
    Integer id;
    String username;
    Set<BankAccountDto> bankAccounts;
}