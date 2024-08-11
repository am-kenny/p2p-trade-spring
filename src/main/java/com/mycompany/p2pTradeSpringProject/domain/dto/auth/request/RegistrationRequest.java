package com.mycompany.p2pTradeSpringProject.domain.dto.auth.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationRequest { //TODO: Add validation
    String username;
    String password;
    String email;
}
