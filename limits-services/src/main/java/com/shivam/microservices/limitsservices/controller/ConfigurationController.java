package com.shivam.microservices.limitsservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.microservices.limitsservices.util.ConfigurationProps;
import com.shivam.microservices.limitsservices.util.LimitsConfiguration;

@RestController
public class ConfigurationController {

	@Autowired
	ConfigurationProps config;

	@GetMapping("/limits")
	public LimitsConfiguration getLimitsFromConfiguration() {
		return new LimitsConfiguration(config.getMaximum(), config.getMinimum());
	}

}
