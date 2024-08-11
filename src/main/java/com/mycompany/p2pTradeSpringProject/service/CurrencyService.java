package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.CurrencyDto;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOCurrency;
import com.mycompany.p2pTradeSpringProject.service.mapper.CurrencyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CurrencyService {

    private final IDAOCurrency daoCurrency;

    @Transactional(readOnly = true)
    public List<CurrencyDto> getAllCurrencies() {
        return daoCurrency.findAll()
                .stream()
                .map(CurrencyMapper::toDto)
                .toList();
    }

}
