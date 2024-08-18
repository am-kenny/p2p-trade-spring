package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.auth.request.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.auth.response.RegistrationResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
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
public class AuthService {

    private final IDAOUser daoUser;

    private final ValidationWrapper validationWrapper;


    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {

        Set<ValidationError> errors = validateRegistrationRequest(request);

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


    private Set<ValidationError> validateRegistrationRequest(RegistrationRequest request) {

        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (daoUser.existsByUsername(request.getUsername())) {
            errors.add(ValidationError.builder()
                    .message("User with this username already exists")
                    .build());
        }

        if (daoUser.existsByEmail(request.getEmail())) {
            errors.add(ValidationError.builder()
                    .message("User with this email already exists")
                    .build());
        }

        return errors;
    }
}
