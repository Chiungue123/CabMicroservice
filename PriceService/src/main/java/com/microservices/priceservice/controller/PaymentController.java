package com.microservices.priceservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.priceservice.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService service;

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/payment/{pickUpTime}/{vehicleType}")
	public String calculatePayment(@PathVariable("pickUpTime") String pickUpTime, @PathVariable("vehicleType") String vehicleType) throws Exception {
		
		String fare = service.calculatePayment(pickUpTime, vehicleType);
		
		return fare;
	}
	
}
