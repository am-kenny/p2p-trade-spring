package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.*;
import com.mycompany.p2pTradeSpringProject.dto.Error;
import com.mycompany.p2pTradeSpringProject.exception.TradeNotFoundException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOTrade;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import com.mycompany.p2pTradeSpringProject.utils.TradeMapper;
import com.mycompany.p2pTradeSpringProject.utils.UserMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static com.mycompany.p2pTradeSpringProject.persistence.repositories.specifications.TradeSpecifications.*;

@Service
@Transactional
@AllArgsConstructor
public class TradeService {

    private final Validator validator;

    private final IDAOTrade daoTrade;

    private final CurrencyService currencyService;

    public CreateTradeResponse createTrade(CreateTradeRequest request, Integer userId) {

        Set<ConstraintViolation<CreateTradeRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            return CreateTradeResponse.builder()
                    .success(false)
                    .errors(violations.stream()
                            .map(violation -> Error.builder()
                                    .message(violation.getMessage())
                                    .build())
                            .toList())
                    .build();
        }

        Trade trade = TradeMapper.toEntity(request);
        trade.setInitiatorUser(UserMapper.toEntity(userId));
        Integer tradeId = daoTrade.create(trade);

        return CreateTradeResponse.builder()
                .success(true)
                .tradeId(tradeId)
                .build();
    }

    @Transactional(readOnly = true)
    public OpenTradeDto getOpenTradeById(int id) {
        return TradeMapper.mapToOpenTradeDto(
                daoTrade.findById(id)
                        .orElseThrow(() -> new TradeNotFoundException("Trade not found"))
        );
    }

    @Transactional(readOnly = true)
    public GetOpenTradesResponse getOpenTrades(GetOpenTradesRequest request) { //TODO: Filter only open trades

        Logger.getGlobal().info("GetOpenTradesRequest: " + "getBuy:" + request.getBuy() + " tradeCurrencyId:" + request.getTradeCurrencyId() + " exchangeCurrencyId:" + request.getExchangeCurrencyId());

        List<OpenTradeDto> openTradeList;
        Specification<Trade> specification = isOpen();

        if (request.getBuy() != null) {
            specification = specification.and(isSeller(request.getBuy()));
        }
        if (request.getTradeCurrencyId() != null) {
            specification = specification.and(tradeCurrencyId(request.getTradeCurrencyId()));
        }
        if (request.getExchangeCurrencyId() != null) {
            specification = specification.and(exchangeCurrencyId(request.getExchangeCurrencyId()));
        }

        openTradeList = daoTrade.findAll(specification)
                .stream()
                .map(TradeMapper::mapToOpenTradeDto)
                .toList();

        List<CurrencyDto> currencies = currencyService.getAllCurrencies();

        return GetOpenTradesResponse.builder()
                .openTrades(openTradeList)
                .currencies(currencies)
                .build();
    }

}
