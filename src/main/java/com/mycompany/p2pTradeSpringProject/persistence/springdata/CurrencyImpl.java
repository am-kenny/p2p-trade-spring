package com.mycompany.p2pTradeSpringProject.persistence.springdata;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOCurrency;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CurrencyImpl implements IDAOCurrency {

    private final CurrencyRepository currencyRepository;

    @Override
    public Optional<Currency> findById(int id) {
        return currencyRepository.findById(id);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public List<Currency> findAll(int maxResults, int firstResult) {
        return currencyRepository.findAll();
    }

    @Override
    public Integer create(Currency currency) {
        return currencyRepository.save(currency).getId();
    }

    @Override
    public void update(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public void delete(Currency currency) {
        currencyRepository.delete(currency);
    }

    @Override
    public Long count() {
        return currencyRepository.count();
    }
}
