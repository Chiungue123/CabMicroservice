package com.microservices.bookingservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookingAOP {
	
	@Autowired
    private Environment environment;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.microservices.bookingservice.controller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		
		String port = environment.getProperty("local.server.port");
		logger.info("@BEFORE BOOKING AOP: Handled by instance on port: {}" + port + ", Method: {}" + joinPoint.getSignature().getName());
		
	}
}
