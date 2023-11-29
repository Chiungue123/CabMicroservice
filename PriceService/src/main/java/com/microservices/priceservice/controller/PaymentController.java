package com.microservices.priceservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.priceservice.bean.Booking;
import com.microservices.priceservice.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService service;

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/payment")
	public Booking calculatePayment(@RequestBody Booking booking) throws Exception {
		
		booking.setFare(service.calculatePayment(booking));
		
		return booking;
	}
	
}
