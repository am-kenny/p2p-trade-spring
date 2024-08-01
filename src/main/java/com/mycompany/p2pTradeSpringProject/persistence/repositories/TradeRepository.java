package com.mycompany.p2pTradeSpringProject.persistence.repositories;

import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
    List<Trade> findByTradeCurrency(Currency currency);
    List<Trade> findByIsSeller(Boolean isSeller);
    List<Trade> findByTradeCurrencyAndIsSeller(Currency currency, Boolean isSeller);
}