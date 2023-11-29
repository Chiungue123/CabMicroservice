package com.microservices.priceservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.microservices.priceservice.bean.Booking;

@Aspect
@Component
public class PriceAOP {

	@Autowired
    private Environment environment;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(Booking com.microservices.priceservice.controller.calculatePayment(..)) and args(booking)")
	public void logBefore(JoinPoint joinPoint, Booking booking) {
		
		String port = environment.getProperty("local.server.port");
		logger.info("@BEFORE PRICE AOP: Handled by instance on port: {}" + port + ", Method: {}" + joinPoint.getSignature().getName());
		logger.info("@BEFORE PRICE AOP: Calculating Payment for Booking Id: {}", booking.getId());
	}
	
	@After("execution(Booking com.microservices.priceservice.controller.calculatePayment(..)) and args(booking)")
	public void logAfter(JoinPoint joinPoint, Booking booking) {
		
		String port = environment.getProperty("local.server.port");
		logger.info("@AFTER PRICE AOP: Handled by instance on port: {}" + port + ", Method: {}" + joinPoint.getSignature().getName());
		logger.info("@AFTER PRICE AOP: Booking Payment: {}", booking.getFare(), "for Id: {}", booking.getId());
	}
}



/* BREAKING DOWN @BEFORE PARAMETER
 * ================================
 * execution(* com.microservices.priceservice.controller.*.*(..))
 * ===============================================================
 * 1) execution
 * - This is a pointcut designator that matches the execution of methods. 
 * - It's one of the most common pointcut designators in AspectJ.
 * 
 * 2) First *: 
 * - This wildcard matches any return type. 
 * - It means the advice will be applied regardless of whether the method returns a String, void, an object, etc.
 *
 * 3) com.microservices.priceservice.controller.*
 * - This specifies the package and class.
 * - The * here means the advice applies to all classes within the com.microservices.priceservice.controller package.
 * 
 * 4) Second *
 * - This wildcard indicates that the advice should apply to all methods, 
 * - regardless of their name, within the specified classes.
 * 
 * 5) (..)
 * - This represents the method arguments. (..) is used to match any number (including zero) of arguments of any type. 
 * - If you wanted to be more specific, you could list the argument types within the parentheses.
 * 
 * ===================
 * IN A NUTSHELL
 * ===================
 * - the logBefore method will be executed before the execution of any 
 * - method in any class in the priceservice.controller package, 
 * - regardless of the method's return type or arguments
 */