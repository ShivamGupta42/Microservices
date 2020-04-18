package com.shivam.microservices.currencyconversionservice.externalMicroservices;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shivam.microservices.currencyconversionservice.dto.CurrencyConversionBean;


//@FeignClient(name = "currency-exchange-service", url = "localhost:8000") <-Before Ribbon
//@FeignClient(name = "currency-exchange-service") <- After Ribbon
@FeignClient(name = "netflix-zuul-api-gateway-server") // <-After Zuul 
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getExchangeRate(@PathVariable String from, @PathVariable String to);
}


