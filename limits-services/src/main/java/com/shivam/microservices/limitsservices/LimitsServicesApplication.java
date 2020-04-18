package com.shivam.microservices.limitsservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LimitsServicesApplication {

	
	
	public static void main(String[] args) {
		
		SpringApplication.run(LimitsServicesApplication.class, args);
	}

}
