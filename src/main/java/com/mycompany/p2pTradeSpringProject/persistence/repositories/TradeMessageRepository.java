package com.mycompany.p2pTradeSpringProject.persistence.repositories;

import com.mycompany.p2pTradeSpringProject.persistence.entities.TradeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeMessageRepository extends JpaRepository<TradeMessage, Integer> {
}