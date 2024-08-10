package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.dto.CreateBankAccountRequest;
import com.mycompany.p2pTradeSpringProject.dto.CreateBankAccountResponse;
import com.mycompany.p2pTradeSpringProject.dto.Error;
import com.mycompany.p2pTradeSpringProject.dto.GetBankAccountsResponse;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBankAccount;
import com.mycompany.p2pTradeSpringProject.persistence.entities.BankAccount;
import com.mycompany.p2pTradeSpringProject.utils.BankAccountMapper;
import com.mycompany.p2pTradeSpringProject.utils.UserMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountService {

    private final Validator validator;

    IDAOBankAccount daoBankAccount;

    @Transactional(readOnly = true)
    public GetBankAccountsResponse getUsersBankAccounts(Integer userId) {
        List<BankAccountDto> bankAccounts = daoBankAccount.findByUserId(userId)
                .stream()
                .map(BankAccountMapper::toDto)
                .toList();

        return GetBankAccountsResponse.builder()
                .bankAccounts(bankAccounts)
                .build();

    }

    @Transactional
    public CreateBankAccountResponse createBankAccountForUser   (CreateBankAccountRequest request, Integer userId) {

        Set<ConstraintViolation<CreateBankAccountRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            return CreateBankAccountResponse.builder()
                    .success(false)
                    .errors(violations.stream()
                            .map(violation -> Error.builder()
                                    .message(violation.getMessage())
                                    .build())
                            .toList())
                    .build();
        }

        BankAccount bankAccount = BankAccountMapper.toEntity(request);
        bankAccount.setUser(UserMapper.toEntity(userId));

        Integer bankAccountId = daoBankAccount.create(bankAccount);

        return CreateBankAccountResponse.builder()
                .success(true)
                .bankAccountId(bankAccountId)
                .build();

    }

}
