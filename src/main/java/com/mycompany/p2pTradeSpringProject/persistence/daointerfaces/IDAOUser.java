package com.mycompany.p2pTradeSpringProject.persistence.daointerfaces;

import com.mycompany.p2pTradeSpringProject.persistence.entities.User;

import java.util.Optional;


public interface IDAOUser extends IGenericDAO<User> {
    Optional<User> findByUsernamePassword(String username, String password);

    Optional<User> findByUsername(String username);
}
