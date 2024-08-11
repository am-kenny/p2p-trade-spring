package com.mycompany.p2pTradeSpringProject.service.mapper;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankAccountRequest;
import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;

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

    public static BankAccount toEntity(CreateBankAccountRequest request) {
        if (request == null) {
            return null;
        }
        return BankAccount.builder()
                .cardNumber(request.getCardNumber())
                .bank(BankMapper.toEntity(request.getBankId()))
                .currency(CurrencyMapper.toEntity(request.getCurrencyId()))
                .cardholderName(request.getCardholderName())
                .build();
    }

}
