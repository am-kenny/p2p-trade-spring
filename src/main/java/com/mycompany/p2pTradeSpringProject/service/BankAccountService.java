package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.CreateBankAccountRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.CreateBankAccountResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.response.GetBankAccountsResponse;
import com.mycompany.p2pTradeSpringProject.exception.custom.BankAccountNotFoundException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBankAccount;
import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;
import com.mycompany.p2pTradeSpringProject.service.mapper.BankAccountMapper;
import com.mycompany.p2pTradeSpringProject.service.mapper.UserMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    @Transactional(readOnly = true)
    public BankAccountDto getBankAccountById(Integer bankAccountId, Integer userId) {
        BankAccount bankAccount = daoBankAccount.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        if (!bankAccount.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Bank Account does not belong to the user"); //Is this the correct exception to throw?
        }

        return BankAccountMapper.toDto(bankAccount);
    }

    @Transactional
    public CreateBankAccountResponse createBankAccountForUser   (CreateBankAccountRequest request, Integer userId) {
        Set<Error> errors = validateRegistrationRequest(request);

        if (!errors.isEmpty()) {
            return CreateBankAccountResponse.builder()
                    .success(false)
                    .errors(errors)
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

    private Set<Error> validateRegistrationRequest(CreateBankAccountRequest request) {
        Set<ConstraintViolation<CreateBankAccountRequest>> violations = validator.validate(request);
        Set<Error> errors = new HashSet<>();

        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.add(Error.builder()
                    .message(violation.getMessage())
                    .build()));
        }

        return errors;
    }

}
