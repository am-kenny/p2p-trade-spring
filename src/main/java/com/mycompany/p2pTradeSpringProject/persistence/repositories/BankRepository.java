package com.mycompany.p2pTradeSpringProject.persistence.repositories;

import com.mycompany.p2pTradeSpringProject.domain.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}