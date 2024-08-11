package com.mycompany.p2pTradeSpringProject.domain.dto.bank.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateBankRequest {

    @NotBlank
    String name;

}
