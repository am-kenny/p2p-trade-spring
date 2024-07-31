package com.mycompany.p2pTradeSpringProject.dto;

import com.mycompany.p2pTradeSpringProject.persistence.entities.BankAccount;
import com.mycompany.p2pTradeSpringProject.persistence.entities.TradeFeedback;
import com.mycompany.p2pTradeSpringProject.persistence.entities.UserType;
import com.mycompany.p2pTradeSpringProject.persistence.entities.UserVerification;
import lombok.Data;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String email;

    private UserVerification userVerification;

    private Instant createdAt;

    private Set<TradeFeedback> tradeFeedbacks = new LinkedHashSet<>();

    private UserType userType;

    private Set<BankAccount> bankAccounts = new LinkedHashSet<>();
}
