package com.mycompany.p2pTradeSpringProject.persistence.springdata;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOFeedback;
import com.mycompany.p2pTradeSpringProject.persistence.entities.TradeFeedback;
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
    public void create(TradeFeedback tradeFeedback) {

    }

    @Override
    public void update(TradeFeedback tradeFeedback, String[] params) {

    }

    @Override
    public void delete(TradeFeedback tradeFeedback) {

    }

    @Override
    public Long count() {
        return tradeFeedbackRepository.count();
    }
}
