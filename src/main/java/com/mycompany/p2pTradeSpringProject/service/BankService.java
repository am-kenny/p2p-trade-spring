package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.CreateBankResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.GetBanksResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBank;
import com.mycompany.p2pTradeSpringProject.service.mapper.BankMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class BankService {

    private final Validator validator;

    IDAOBank daoBank;


    @Transactional(readOnly = true)
    public GetBanksResponse getAllBanks() {
        return GetBanksResponse.builder()
                .banks(daoBank.findAll()
                        .stream()
                        .map(BankMapper::toDto)
                        .toList())
                .build();
    }

    @Transactional
    public CreateBankResponse createBank(CreateBankRequest request) {
        Set<Error> errors = validateCreateBankRequest(request);

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

    private Set<Error> validateCreateBankRequest(CreateBankRequest request) {
        Set<ConstraintViolation<CreateBankRequest>> violations = validator.validate(request);
        Set<Error> errors = new HashSet<>();

        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.add(Error.builder()
                    .message(violation.getMessage())
                    .build()));
        }

        return errors;
    }
}
