package com.mycompany.p2pTradeSpringProject.persistence.daointerfaces;

import com.mycompany.p2pTradeSpringProject.domain.entity.BankAccount;

import java.util.List;


public interface IDAOBankAccount extends IGenericDAO<BankAccount> {
    List<BankAccount> findByUserId(Integer userId);
}
