package com.mycompany.p2pTradeSpringProject.persistence.daojpa;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOFeedback;
import com.mycompany.p2pTradeSpringProject.domain.entity.TradeFeedback;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.TradeFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TradeFeedbackImpl implements IDAOFeedback {

    private final TradeFeedbackRepository tradeFeedbackRepository;

    @Override
    public Optional<TradeFeedback> findById(int id) {
        return tradeFeedbackRepository.findById(id);
    }

    @Override
    public List<TradeFeedback> findAll() {
        return tradeFeedbackRepository.findAll();
    }

    @Override
    public List<TradeFeedback> findAll(int maxResults, int firstResult) {
        return tradeFeedbackRepository.findAll();
    }

    @Override
    public Integer create(TradeFeedback tradeFeedback) {
        return tradeFeedbackRepository.save(tradeFeedback).getId();
    }

    @Override
    public void update(TradeFeedback tradeFeedback) {
        tradeFeedbackRepository.save(tradeFeedback);
    }

    @Override
    public void delete(TradeFeedback tradeFeedback) {
        tradeFeedbackRepository.delete(tradeFeedback);
    }

    @Override
    public Long count() {
        return tradeFeedbackRepository.count();
    }
}
