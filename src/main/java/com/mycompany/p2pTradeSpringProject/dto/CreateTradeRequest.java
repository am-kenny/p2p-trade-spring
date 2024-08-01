package com.mycompany.p2pTradeSpringProject.dto;

import com.mycompany.p2pTradeSpringProject.persistence.entities.Currency;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTradeRequest {
    User user;
    Boolean isSeller;
    Currency tradeCurrency;
    Float amount;
    Currency exchangeCurrency;
    Float exchangeRate;
}
