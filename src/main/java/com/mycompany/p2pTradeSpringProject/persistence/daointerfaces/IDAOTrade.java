package com.mycompany.p2pTradeSpringProject.persistence.daointerfaces;

import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;

import java.util.List;


public interface IDAOTrade extends IGenericDAO<Trade> {
    List<Trade> findByCurrency(Currency currency);
    List<Trade> findByIsSeller(Boolean isSeller);
    List<Trade> findByCurrencyAndIsSeller(Currency currency, Boolean isSeller);
}
