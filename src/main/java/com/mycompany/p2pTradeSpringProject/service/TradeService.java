package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.OpenTradeDto;
import com.mycompany.p2pTradeSpringProject.domain.dto.trade.request.CreateTradeRequest;
import com.mycompany.p2pTradeSpringProject.exception.custom.TradeNotFoundException;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOTrade;
import com.mycompany.p2pTradeSpringProject.domain.entity.Trade;
import com.mycompany.p2pTradeSpringProject.service.mapper.TradeMapper;
import com.mycompany.p2pTradeSpringProject.service.mapper.UserMapper;
import com.mycompany.p2pTradeSpringProject.component.ValidationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mycompany.p2pTradeSpringProject.persistence.repositories.specifications.TradeSpecifications.*;

@Service
@Transactional
@AllArgsConstructor
public class TradeService {

    private final IDAOTrade daoTrade;

    private final ValidationWrapper validationWrapper;


    @Transactional
    public Integer createTrade(CreateTradeRequest request, Integer userId) {

        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        Trade trade = TradeMapper.toEntity(request);
        trade.setInitiatorUser(UserMapper.toEntity(userId));

        return daoTrade.create(trade);
    }

    @Transactional(readOnly = true)
    public OpenTradeDto getOpenTradeById(int id) {
        return TradeMapper.mapToOpenTradeDto(
                daoTrade.findById(id)
                        .orElseThrow(() -> new TradeNotFoundException("Trade not found"))
        );
    }

    @Transactional(readOnly = true)
    public List<OpenTradeDto> getOpenTrades(Map<String, String> params) {
        Specification<Trade> specification = isOpen();

        if (params != null && !params.isEmpty()) {
            if (params.containsKey("buy") && !params.get("buy").isBlank()) {
                specification = specification.and(isSeller(Boolean.parseBoolean(params.get("buy"))));
            }
            if (params.containsKey("tradeCurrencyId") && !params.get("tradeCurrencyId").isBlank()) {
                specification = specification.and(tradeCurrencyId(Integer.parseInt(params.get("tradeCurrencyId"))));
            }
            if (params.containsKey("exchangeCurrencyId") && !params.get("exchangeCurrencyId").isBlank()) {
                specification = specification.and(exchangeCurrencyId(Integer.parseInt(params.get("exchangeCurrencyId"))));
            }
        }

        return daoTrade.findAll(specification)
                .stream()
                .map(TradeMapper::mapToOpenTradeDto)
                .toList();
    }

}
