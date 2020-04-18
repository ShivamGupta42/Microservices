package com.shivam.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.microservices.currencyexchangeservice.dto.ExchangeValue;
import com.shivam.microservices.currencyexchangeservice.repository.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {

	@Autowired
	Environment env;

	@Autowired
	ExchangeValueRepo repo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeRate(@PathVariable String from, @PathVariable String to) {
		System.out.println("Exchange request from:" + from + " to:" + to);
		ExchangeValue res = repo.findByFromAndTo(from, to);
		return new ExchangeValue(res.getId(), from, to, res.getConversionMultiple(), env.getProperty("local.server.port"));
	}

}
