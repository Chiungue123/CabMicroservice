package com.microservices.gatewayservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/Payment")
	public String payment() {
		
		logger.debug("PriceController: Returning Payment Page");
		
		return "payment";
	}
	
}
