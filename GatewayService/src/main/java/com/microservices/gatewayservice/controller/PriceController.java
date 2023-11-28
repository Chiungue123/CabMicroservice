package com.microservices.gatewayservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.gatewayservice.dto.BookingsDTO;
import com.microservices.gatewayservice.proxy.PaymentClient;

@RestController
@RequestMapping("/gateway/payment")
public class PriceController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final PaymentClient paymentClient;
	
	PriceController(PaymentClient paymentClient){
		this.paymentClient =  paymentClient;
	}
	
	@PostMapping("/calculate")
	public ResponseEntity<BookingsDTO> payment(@RequestBody BookingsDTO book) {
		
		logger.debug("Payment Gateway: Calculating Payment for Booking: {}", book);
		BookingsDTO booking = paymentClient.calculatePayment(book);
		return ResponseEntity.ok(booking);
	}
	
}
