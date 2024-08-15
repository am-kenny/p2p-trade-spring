package com.mycompany.p2pTradeSpringProject.service.mapper;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.BankAccountRequest;
import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;

public class BankAccountMapper {

    public static BankAccount toEntity(BankAccountDto dto) {
        if (dto == null) {
            return null;
        }
        return BankAccount.builder()
                .id(dto.getId())
                .accountNumber(dto.getAccountNumber())
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
                .accountNumber(entity.getAccountNumber())
                .bank(BankMapper.toDto(entity.getBank()))
                .currency(CurrencyMapper.toDto(entity.getCurrency()))
                .cardholderName(entity.getCardholderName())
                .build();
    }

    public static BankAccount toEntity(BankAccountRequest request) {
        if (request == null) {
            return null;
        }
        return BankAccount.builder()
                .accountNumber(request.getAccountNumber())
                .bank(BankMapper.toEntity(request.getBankId()))
                .currency(CurrencyMapper.toEntity(request.getCurrencyId()))
                .cardholderName(request.getCardholderName())
                .build();
    }

    public static BankAccount toEntity(Integer id) {
        if (id == null) {
            return null;
        }
        return BankAccount.builder()
                .id(id)
                .build();
    }

}
