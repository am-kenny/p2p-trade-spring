package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.auth.request.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.auth.response.RegistrationResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.service.mapper.UserMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final Validator validator;

    private final IDAOUser daoUser;


    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {

        Set<Error> errors = validateRegistrationRequest(request);

        if (!errors.isEmpty()) {
            return RegistrationResponse.builder()
                    .success(false)
                    .errors(errors)
                    .build();
        }


        User user = UserMapper.toEntity(request);
        Integer userId = daoUser.create(user);

        return RegistrationResponse.builder()
                .success(true)
                .userId(userId)
                .build();

    }


    private Set<Error> validateRegistrationRequest(RegistrationRequest request) {
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);
        Set<Error> errors = new HashSet<>();

        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.add(Error.builder()
                    .message(violation.getMessage())
                    .build()));
        }

        if (daoUser.existsByUsername(request.getUsername())) {
            errors.add(Error.builder()
                    .message("User with this username already exists")
                    .build());
        }

        if (daoUser.existsByEmail(request.getEmail())) {
            errors.add(Error.builder()
                    .message("User with this email already exists")
                    .build());
        }

        return errors;
    }
}
