package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.exception.BadCredentialsException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUserType;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AuthService {

    private final IDAOUserType daoUserType;
    private final IDAOUser daoUser; // TODO: Ask what if there several implementations

    public User login(String username, String password) throws BadCredentialsException {
        return daoUser.findByUsernamePassword(username, password).orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
    }

    public void register(User user) {
        daoUser.create(user);
    }

    public User findById(int id) {
        return daoUser.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
