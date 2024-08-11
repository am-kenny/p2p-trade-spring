package com.mycompany.p2pTradeSpringProject.exception.custom;

import jakarta.persistence.EntityNotFoundException;

public class TradeNotFoundException extends EntityNotFoundException {
    public TradeNotFoundException(String message) {
        super(message);
    }
}
