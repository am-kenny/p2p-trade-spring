package com.mycompany.p2pTradeSpringProject.exception.custom;

import jakarta.persistence.EntityNotFoundException;

public class BankNotFoundException extends EntityNotFoundException {
    public BankNotFoundException(String message) {
        super(message);
    }
}
