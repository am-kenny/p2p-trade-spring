package com.mycompany.p2pTradeSpringProject.persistence.repositories;

import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    List<BankAccount> findByUserId(Integer userId);
}