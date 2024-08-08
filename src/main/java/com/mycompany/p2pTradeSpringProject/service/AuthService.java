package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.utils.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final IDAOUser daoUser;

    public void register(RegistrationRequest request) {
        User user = UserMapper.toEntity(request);
        daoUser.create(user);
    }
}
