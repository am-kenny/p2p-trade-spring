package com.mycompany.p2pTradeSpringProject.utils;

import com.mycompany.p2pTradeSpringProject.dto.BankDto;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Bank;

public class BankMapper {

    public static Bank toEntity(BankDto dto) {
        if (dto == null) {
            return null;
        }
        return Bank.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public static BankDto toDto(Bank entity) {
        if (entity == null) {
            return null;
        }
        return BankDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
