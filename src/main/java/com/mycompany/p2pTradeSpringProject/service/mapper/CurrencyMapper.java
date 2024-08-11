package com.mycompany.p2pTradeSpringProject.service.mapper;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.domain.entity.Currency;

public class CurrencyMapper {

    public static Currency toEntity(CurrencyDto dto) {
        if (dto == null) {
            return null;
        }
        return Currency.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .build();
    }

    public static CurrencyDto toDto(Currency entity) {
        if (entity == null) {
            return null;
        }
        return CurrencyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .build();
    }

    public static Currency toEntity(int id) { //TODO: Remove this method
        return Currency.builder()
                .id(id)
                .build();
    }

}
