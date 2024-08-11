package com.mycompany.p2pTradeSpringProject.persistence.daojpa;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOBank;
import com.mycompany.p2pTradeSpringProject.domain.entity.Bank;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BankImpl implements IDAOBank {

    private final BankRepository bankRepository;

    @Override
    public Optional<Bank> findById(int id) {
        return bankRepository.findById(id);
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public List<Bank> findAll(int maxResults, int firstResult) {
        return bankRepository.findAll();
    }

    @Override
    public Integer create(Bank bank) {
        return bankRepository.save(bank).getId();
    }

    @Override
    public void update(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }

    @Override
    public Long count() {
        return bankRepository.count();
    }
}
