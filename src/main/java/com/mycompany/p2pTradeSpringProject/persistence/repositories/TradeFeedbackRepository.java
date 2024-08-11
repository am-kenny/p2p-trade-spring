package com.mycompany.p2pTradeSpringProject.persistence.repositories;

import com.mycompany.p2pTradeSpringProject.domain.entity.TradeFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeFeedbackRepository extends JpaRepository<TradeFeedback, Integer> {
}