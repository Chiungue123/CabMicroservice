package com.microservices.gatewayservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookingController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("Book")
	public String book() {
		
		logger.debug("Booking Controller: Rendering Booking Page");
		
		return "book";
	}
	
	@GetMapping("View")
	public String view() {
		
		logger.debug("Booking Controller: Rendering View Bookings Page");
		
		return "view";
	}
	
	@PostMapping("Book")
	public ModelAndView booked() {
		
		logger.debug("Booking Controller: Returning ModelAndView");
		
		return null;
	}

}
