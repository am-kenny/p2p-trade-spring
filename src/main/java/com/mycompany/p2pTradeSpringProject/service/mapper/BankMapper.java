package com.mycompany.p2pTradeSpringProject.service.mapper;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankRequest;
import com.mycompany.p2pTradeSpringProject.domain.entity.Bank;

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

    public static Bank toEntity(Integer bankId) {
        return Bank.builder()
                .id(bankId)
                .build();
    }

    public static Bank toEntity(CreateBankRequest request) {
        return Bank.builder()
                .name(request.getName())
                .build();
    }
}
