package com.mycompany.p2pTradeSpringProject.persistence.daointerfaces;

import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface IDAOTrade extends IGenericDAO<Trade> {
    List<Trade> findAll(Specification<Trade> specification);
}
