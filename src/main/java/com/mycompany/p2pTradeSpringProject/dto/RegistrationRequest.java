package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationRequest {
    String username;
    String password;
    String email;
}
