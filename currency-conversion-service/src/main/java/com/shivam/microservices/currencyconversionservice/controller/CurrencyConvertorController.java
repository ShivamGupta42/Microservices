package com.shivam.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shivam.microservices.currencyconversionservice.dto.CurrencyConversionBean;
import com.shivam.microservices.currencyconversionservice.externalMicroservices.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConvertorController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyConvertorController.class);

	
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-convertor/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getConvertedCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> respEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean response = respEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, response.getPort(),
				response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()));

	}
	
	
	

	@GetMapping("/currency-convertor2/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getConvertedCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.getExchangeRate(from, to);
        logger.info("{}",response);
		return new CurrencyConversionBean(response.getId(), from, to, response.getPort(),
				response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()));

	}

}
