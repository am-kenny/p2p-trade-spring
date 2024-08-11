package com.mycompany.p2pTradeSpringProject.persistence.daojpa;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOMessage;
import com.mycompany.p2pTradeSpringProject.domain.entity.TradeMessage;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.TradeMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TradeMessageImpl implements IDAOMessage {

    private final TradeMessageRepository tradeMessageRepository;

    @Override
    public Optional<TradeMessage> findById(int id) {
        return tradeMessageRepository.findById(id);
    }

    @Override
    public List<TradeMessage> findAll() {
        return tradeMessageRepository.findAll();
    }

    @Override
    public List<TradeMessage> findAll(int maxResults, int firstResult) {
        return tradeMessageRepository.findAll();
    }

    @Override
    public Integer create(TradeMessage tradeMessage) {
        return tradeMessageRepository.save(tradeMessage).getId();
    }

    @Override
    public void update(TradeMessage tradeMessage) {
        tradeMessageRepository.save(tradeMessage);
    }

    @Override
    public void delete(TradeMessage tradeMessage) {
        tradeMessageRepository.delete(tradeMessage);
    }

    @Override
    public Long count() {
        return tradeMessageRepository.count();
    }
}
