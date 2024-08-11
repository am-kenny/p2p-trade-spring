package com.mycompany.p2pTradeSpringProject.persistence.daointerfaces;

import com.mycompany.p2pTradeSpringProject.domain.entity.UserVerification;


public interface IDAOUserVerification extends IGenericDAO<UserVerification> {
    boolean existsByPassportNumber(String passportNumber);
}
