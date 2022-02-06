package com.gulukal.currencyexchangeservice.repository;

import com.gulukal.currencyexchangeservice.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface CurrencyExchangeRepository
        extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);

    CurrencyExchange findByConversionMultiple(BigDecimal conMuti);


}
