package com.mycompany.p2pTradeSpringProject.domain.dto.bank.response;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateBankResponse {
    boolean success;
    Integer bankId;
    List<Error> errors;
}
