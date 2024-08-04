package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.*;
import com.mycompany.p2pTradeSpringProject.dto.Error;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOTrade;
import com.mycompany.p2pTradeSpringProject.persistence.entities.Trade;
import com.mycompany.p2pTradeSpringProject.utils.TradeMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.mycompany.p2pTradeSpringProject.persistence.repositories.specifications.TradeSpecifications.isOpen;
import static com.mycompany.p2pTradeSpringProject.persistence.repositories.specifications.TradeSpecifications.tradeCurrencyId;

@Service
@Transactional
@AllArgsConstructor
public class TradeService {

    private final Validator validator;

    private final IDAOTrade daoTrade;

    public CreateTradeResponse createTrade(CreateTradeRequest request) {

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
                        .orElseThrow(() -> new IllegalArgumentException("Trade not found"))
        );
    }

    @Transactional(readOnly = true)
    public GetOpenTradesResponse getOpenTrades(GetOpenTradesRequest request) { //TODO: Filter only open trades

        List<OpenTradeDto> openTradeList;
        Specification<Trade> specification = isOpen();

        if (request.getBuy() != null) {
            specification = specification.and(tradeCurrencyId(request.getTradeCurrency().getId()));
        }
        if (request.getTradeCurrency() != null) {
            specification = specification.and(tradeCurrencyId(request.getTradeCurrency().getId()));
        }
        if (request.getExchangeCurrency() != null) {
            specification = specification.and(tradeCurrencyId(request.getExchangeCurrency().getId()));
        }

        openTradeList = daoTrade.findAll(specification)
                .stream()
                .map(TradeMapper::mapToOpenTradeDto)
                .toList();

        return GetOpenTradesResponse.builder()
                .openTrades(openTradeList)
                .build();
    }

}
