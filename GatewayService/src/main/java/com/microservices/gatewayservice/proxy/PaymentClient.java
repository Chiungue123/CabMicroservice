package com.microservices.gatewayservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.gatewayservice.dto.BookingsDTO;

@FeignClient(name = "price-service")
public interface PaymentClient {

	@GetMapping("/payment/calculate")
	BookingsDTO calculatePayment(@RequestBody BookingsDTO booking);
	
}
