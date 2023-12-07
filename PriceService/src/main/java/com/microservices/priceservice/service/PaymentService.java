package com.microservices.priceservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microservices.priceservice.exception.InvalidBookingException;

@Service
public class PaymentService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String calculatePayment(String pickUpTime, String vehicleType) throws Exception{
		
		String[] pickUpTimeSplit = pickUpTime.split(":");
		
		int time = Integer.parseInt(pickUpTimeSplit[0]);
		
		logger.debug("PaymentService: Calculating Payment");
		logger.debug("PaymentService: Pickup Time: {}", time);
		logger.debug("PaymentService: Vehicle Type: {}", vehicleType);
		
		int value1 = 0;
		int value2 = 0;
		
		switch(vehicleType) {
		
			case "Bently" -> value1 = 5;
			case "Audi" -> value1 = 4;
			case "Jeep" -> value1 = 3;
			case "Ford" -> value1 = 2;
			case "Honda" -> value1 = 1;
			default -> value1 = 0;
		}
		
		 if (time < 8) {
		    value2 = 5;
	     } else if (time >= 9 && time < 16) {
	        value2 = 4;
	     } else if (time >= 17 && time < 24) {
	        value2 = 3;
	     } else {
	        value2 = 0;
	     }
	
		if (value1 == 0 || value2 == 0) {
			throw new InvalidBookingException("Invalid Pickup Time: " + time + " and/or Vehicle Type: " + vehicleType);
		}

		Double payment = (15 + (value1 * 5) + (value2 * 4) * 1.12);
		BigDecimal fare = new BigDecimal(payment).setScale(2, RoundingMode.HALF_UP);
		logger.debug("PaymentService: Payment Calculated: {}", fare);
		
		return fare.toString();
	}
	
}
