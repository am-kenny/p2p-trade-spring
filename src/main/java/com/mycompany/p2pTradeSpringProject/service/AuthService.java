package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.LoginRequest;
import com.mycompany.p2pTradeSpringProject.dto.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.exception.BadCredentialsException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final IDAOUser daoUser; // TODO: Ask what if there several implementations

    @Transactional(readOnly = true)
    public User login(LoginRequest loginRequest) throws BadCredentialsException {
        return daoUser.findByUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword()).orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
    }

    public void register(RegistrationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
        daoUser.create(user);
    }
}
