package com.mycompany.p2pTradeSpringProject.domain.dto.common;

import com.mycompany.p2pTradeSpringProject.domain.entity.Currency;
import lombok.Builder;
import lombok.Value;


/**
 * DTO for {@link Currency}
 */
@Value
@Builder
public class CurrencyDto {
    Integer id;
    String name;
    String code;
}