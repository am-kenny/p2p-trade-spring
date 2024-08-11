package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.auth.request.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.service.mapper.UserMapper;
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
