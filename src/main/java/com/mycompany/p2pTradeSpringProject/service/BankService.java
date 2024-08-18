package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.CreateBankResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.GetBanksResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.exception.custom.BankNotFoundException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBank;
import com.mycompany.p2pTradeSpringProject.service.mapper.BankMapper;
import com.mycompany.p2pTradeSpringProject.component.ValidationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class BankService {

    IDAOBank daoBank;

    private final ValidationWrapper validationWrapper;


    @Transactional(readOnly = true)
    public GetBanksResponse getAllBanks() {
        return GetBanksResponse.builder()
                .banks(daoBank.findAll()
                        .stream()
                        .map(BankMapper::toDto)
                        .toList())
                .build();
    }

    @Transactional(readOnly = true)
    public BankDto getBankById(Integer id) {
        return BankMapper.toDto(daoBank.findById(id).orElseThrow(() -> new BankNotFoundException("Bank not found")));
    }

    @Transactional
    public CreateBankResponse createBank(CreateBankRequest request) {
        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (!errors.isEmpty()) {
            return CreateBankResponse.builder()
                    .success(false)
                    .errors(errors)
                    .build();
        }

        Integer bankId = daoBank.create(BankMapper.toEntity(request));

        return CreateBankResponse.builder()
                .success(true)
                .bankId(bankId)
                .build();
    }

}
