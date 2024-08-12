package com.mycompany.p2pTradeSpringProject.exception.custom;

import jakarta.persistence.EntityNotFoundException;

public class BankAccountNotFoundException extends EntityNotFoundException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
