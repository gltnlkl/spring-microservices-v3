package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.entity.CurrencyConversion;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {


    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")

    public CurrencyConversion calculateCurrncyConversion(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity){

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversion> responsEntity =
        new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversion
                .class,uriVariables);

        CurrencyConversion currencyConversion = responsEntity.getBody();


        assert currencyConversion != null;

        return CurrencyConversion.builder().
                id(currencyConversion.getId()).
                from(from).
                to(to).
                quantity(quantity).
                conversionMultiple(currencyConversion.getConversionMultiple()).
                totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple())).
                environment(currencyConversion.getEnvironment()).
                build();
    }

}
