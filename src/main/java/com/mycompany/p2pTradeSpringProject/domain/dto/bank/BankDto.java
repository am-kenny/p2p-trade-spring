package com.mycompany.p2pTradeSpringProject.domain.dto.bank;

import com.mycompany.p2pTradeSpringProject.domain.entity.Bank;
import lombok.Builder;
import lombok.Value;


/**
 * DTO for {@link Bank}
 */
@Value
@Builder
public class BankDto {
    Integer id;
    String name;
}