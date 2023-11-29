package com.microservices.bookingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservices.bookingservice.jpa.Booking;

@FeignClient( name = "price-service")
public interface PaymentProxy {

	@GetMapping("/payment")
	Booking calculatePayment(Booking booking);
}
