package com.gulukal.currencyexchangeservice.controller;


import com.gulukal.currencyexchangeservice.entity.CurrencyExchange;
import com.gulukal.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;

    }

    //GU DENEME !!!!
    @GetMapping("/currency-exchange/by/{conMuti}")
    public CurrencyExchange retrieveExchangeValueByConMuti(
            @PathVariable BigDecimal conMuti
    ) {
        CurrencyExchange currencyExchange = repository.findByConversionMultiple(conMuti);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find data for " + conMuti);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }

}
