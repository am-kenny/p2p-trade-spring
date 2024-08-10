package com.mycompany.p2pTradeSpringProject.utils;

import com.mycompany.p2pTradeSpringProject.dto.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.persistence.entities.BankAccount;

public class BankAccountMapper {

    public static BankAccount toEntity(BankAccountDto dto) {
        if (dto == null) {
            return null;
        }
        return BankAccount.builder()
                .id(dto.getId())
                .cardNumber(dto.getCardNumber())
                .bank(BankMapper.toEntity(dto.getBank()))
                .currency(CurrencyMapper.toEntity(dto.getCurrency()))
                .cardholderName(dto.getCardholderName())
                .build();
    }

    public static BankAccountDto toDto(BankAccount entity) {
        if (entity == null) {
            return null;
        }
        return BankAccountDto.builder()
                .id(entity.getId())
                .cardNumber(entity.getCardNumber())
                .bank(BankMapper.toDto(entity.getBank()))
                .currency(CurrencyMapper.toDto(entity.getCurrency()))
                .cardholderName(entity.getCardholderName())
                .build();
    }

}
