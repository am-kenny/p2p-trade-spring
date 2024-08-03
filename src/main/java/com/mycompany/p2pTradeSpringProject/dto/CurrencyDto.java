package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;


/**
 * DTO for {@link com.mycompany.p2pTradeSpringProject.persistence.entities.Currency}
 */
@Value
@Builder
public class CurrencyDto {
    Integer id;
    String name;
    String code;
}