package com.mycompany.p2pTradeSpringProject.exception.custom;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {
    private final Set<ValidationError> validationErrors;

    public ValidationException(Set<ValidationError> validationErrors) {
        super("Validation failed with " + validationErrors.size() + " error(s)");
        this.validationErrors = validationErrors;
    }

}
