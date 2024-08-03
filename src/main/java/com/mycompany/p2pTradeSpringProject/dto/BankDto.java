package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;


/**
 * DTO for {@link com.mycompany.p2pTradeSpringProject.persistence.entities.Bank}
 */
@Value
@Builder
public class BankDto {
    Integer id;
    String name;
}