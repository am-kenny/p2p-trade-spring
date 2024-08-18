package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.auth.request.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.service.mapper.UserMapper;
import com.mycompany.p2pTradeSpringProject.component.ValidationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthService { //TODO: Combine with UserDetailsService?

    private final IDAOUser daoUser;

    private final ValidationWrapper validationWrapper;


    @Transactional
    public Integer register(RegistrationRequest request) {
        Set<ValidationError> errors = validateRegistrationRequest(request);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        User user = UserMapper.toEntity(request);
        return daoUser.create(user);
    }


    private Set<ValidationError> validateRegistrationRequest(RegistrationRequest request) {

        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (daoUser.existsByUsername(request.getUsername())) {
            errors.add(ValidationError.builder()
                    .code("UniqueUsername")
                    .detail("User with this username already exists")
                    .source("username")
                    .build());
        }

        if (daoUser.existsByEmail(request.getEmail())) {
            errors.add(ValidationError.builder()
                    .code("UniqueEmail")
                    .detail("User with this email already exists")
                    .source("email")
                    .build());
        }

        return errors;
    }
}
