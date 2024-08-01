package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.dto.GetTradesRequest;
import com.mycompany.p2pTradeSpringProject.dto.GetTradesResponse;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOTrade;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TradeService {

    private final IDAOTrade daoTrade;

    public void createTrade(CreateTradeRequest request) {

        Trade trade = Trade.builder()
                .initiatorUser(request.getUser())
                .isSeller(request.getIsSeller())
                .tradeCurrency(request.getTradeCurrency())
                .tradeCurrencyAmount(request.getAmount())
                .exchangeCurrency(request.getExchangeCurrency())
                .exchangeRate(request.getExchangeRate())
                .build();

        daoTrade.create(trade);
    }

    public Trade getTradeById(int id) {
        return daoTrade.findById(id).orElse(null);
    }

    public List<Trade> getTrades(GetTradesRequest request) {
        if (request.getBuy() != null && request.getTradeCurrency() != null) {
            return daoTrade.findByCurrencyAndIsSeller(request.getTradeCurrency(), request.getBuy());
        }
        if (request.getBuy() != null) {
            return daoTrade.findByIsSeller(request.getBuy());
        }
        if (request.getTradeCurrency() != null) {
            return daoTrade.findByCurrency(request.getTradeCurrency());
        }

        //TODO: Complete with exchange currency


        return daoTrade.findAll();
    }

}
