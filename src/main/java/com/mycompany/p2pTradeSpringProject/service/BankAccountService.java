package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.component.ValidationWrapper;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.BankAccountDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.bank.request.BankAccountRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.exception.custom.BankAccountNotFoundException;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBankAccount;
import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;
import com.mycompany.p2pTradeSpringProject.service.mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountService {

    private final IDAOBankAccount daoBankAccount;

    private final ValidationWrapper validationWrapper;


    @Transactional(readOnly = true)
    public List<BankAccountDto> getUsersBankAccounts(Integer userId) {

        return daoBankAccount.findByUserId(userId)
                .stream()
                .map(BankAccountMapper::toDto)
                .toList();
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
    public Integer createBankAccountForUser(BankAccountRequest request, Integer userId) {
        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        BankAccount bankAccount = BankAccountMapper.toEntity(request);
        bankAccount.setUser(UserMapper.toEntity(userId));

        return daoBankAccount.create(bankAccount);
    }

    @Transactional
    public Integer editBankAccountForUser(BankAccountRequest request, Integer userId, Integer bankAccountId) {
        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        BankAccount bankAccount = daoBankAccount.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        if (!bankAccount.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Bank Account does not belong to the user"); //Is this the correct exception to throw?
        }

        bankAccount.setBank(BankMapper.toEntity(request.getBankId()));
        bankAccount.setAccountNumber(request.getAccountNumber());
        bankAccount.setCurrency(CurrencyMapper.toEntity(request.getCurrencyId()));
        bankAccount.setCardholderName(request.getCardholderName());

        daoBankAccount.update(bankAccount);

        return bankAccountId;
    }

    @Transactional
    public Integer deleteBankAccountForUser(Integer userId, Integer bankAccountId) {
        BankAccount bankAccount = daoBankAccount.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        if (!bankAccount.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Bank Account does not belong to the user"); //Is this the correct exception to throw?
        }

        daoBankAccount.delete(BankAccountMapper.toEntity(bankAccountId));

        return bankAccountId;
    }

}
