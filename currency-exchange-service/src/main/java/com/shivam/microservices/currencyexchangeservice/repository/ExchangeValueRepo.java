package com.shivam.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.microservices.currencyexchangeservice.dto.ExchangeValue;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue, Long>{
	
	ExchangeValue findByFromAndTo(String from,String to);

}
