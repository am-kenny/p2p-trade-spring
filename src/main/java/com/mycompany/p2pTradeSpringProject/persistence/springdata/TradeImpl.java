package com.mycompany.p2pTradeSpringProject.persistence.springdata;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOTrade;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TradeImpl implements IDAOTrade {

    private final TradeRepository tradeRepository;

    @Override
    public Optional<Trade> findById(int id) {
        return tradeRepository.findById(id);
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public List<Trade> findAll(int maxResults, int firstResult) {
        return tradeRepository.findAll();
    }

    @Override
    public void create(Trade trade) {
        tradeRepository.save(trade);
    }

    @Override
    public void update(Trade trade) {
        tradeRepository.save(trade);
    }

    @Override
    public void delete(Trade trade) {
        tradeRepository.delete(trade);
    }

    @Override
    public Long count() {
        return tradeRepository.count();
    }

    @Override
    public List<Trade> findByCurrency(Currency currency) {
        return tradeRepository.findByTradeCurrency(currency);
    }

    @Override
    public List<Trade> findByIsSeller(Boolean isSeller) {
        return tradeRepository.findByIsSeller(isSeller);
    }

    @Override
    public List<Trade> findByCurrencyAndIsSeller(Currency currency, Boolean isSeller) {
        return tradeRepository.findByTradeCurrencyAndIsSeller(currency, isSeller);
    }
}
