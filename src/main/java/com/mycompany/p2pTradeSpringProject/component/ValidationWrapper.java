package com.mycompany.p2pTradeSpringProject.component;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationWrapper {

    private final Validator validator;

    public <T> Set<ValidationError>  validateObject(T object) {
        Set<ValidationError> errors = new HashSet<>();

        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.add(mapViolationToValidationError(violation)));
        }
        return errors;
    }

    private ValidationError mapViolationToValidationError(ConstraintViolation<?> violation) {
        return ValidationError.builder()
                .code(violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName())
                .message(violation.getMessage())
                .path(violation.getPropertyPath().toString())
                .build();
    }

}
