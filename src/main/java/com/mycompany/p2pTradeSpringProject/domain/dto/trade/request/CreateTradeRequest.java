package com.mycompany.p2pTradeSpringProject.domain.dto.trade.request;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateTradeRequest {

    @NotNull(message = "You should specify if you want to sell or buy")
    Boolean isSeller;

    @NotNull(message = "Trade currency is required")
    Integer tradeCurrencyId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    Float amount;

    @NotNull(message = "Exchange currency is required")
    Integer exchangeCurrencyId;

    @NotNull(message = "Exchange rate is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Exchange rate must be greater than 0")
    Float exchangeRate;

    @AssertFalse(message = "Currency must be different")
    boolean isSameCurrency() {
        return tradeCurrencyId.equals(exchangeCurrencyId);
    }

}
