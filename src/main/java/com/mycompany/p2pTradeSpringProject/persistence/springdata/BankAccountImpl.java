package com.mycompany.p2pTradeSpringProject.persistence.springdata;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBankAccount;
import com.mycompany.p2pTradeSpringProject.persistence.entities.BankAccount;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BankAccountImpl implements IDAOBankAccount {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public Optional<BankAccount> findById(int id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public List<BankAccount> findAll(int maxResults, int firstResult) {
        return bankAccountRepository.findAll();
    }

    @Override
    public Integer create(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount).getId();
    }

    @Override
    public void update(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void delete(BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }

    @Override
    public Long count() {
        return bankAccountRepository.count();
    }
}
