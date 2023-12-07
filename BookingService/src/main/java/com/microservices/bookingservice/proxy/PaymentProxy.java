package com.microservices.bookingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "price-service")
public interface PaymentProxy {

	@GetMapping("/payment/{pickUpTime}/{vehicleType}")
	String calculatePayment(@PathVariable("pickUpTime") String pickUpTime, @PathVariable("vehicleType") String vehicleType);
}