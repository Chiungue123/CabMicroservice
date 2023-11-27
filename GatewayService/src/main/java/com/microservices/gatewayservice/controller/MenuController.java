package com.microservices.gatewayservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/")
	public String menu() {
		
		logger.debug("MenuController: Rendering Menu");
		
		return "index";
	}
	
}
