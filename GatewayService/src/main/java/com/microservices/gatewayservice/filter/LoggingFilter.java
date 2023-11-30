package com.microservices.gatewayservice.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// This method is called for every HTTP request passing through the Gateway.
		
		// ServerWebExchange represents the current HTTP request and response.
        // Used here to get request details like the path.
        logger.info("GATEWAY: Path of the request received: {}", exchange.getRequest().getPath());

        return chain.filter(exchange);
	}
}

/* KEY TERMS
 * =========
 *
 * @Component: 
 *   - Marks the class as a Spring component, enabling Spring to manage it as a bean.
 *
 * GlobalFilter: 
 * 	 - Interface for filters that apply globally to all routes in the Spring Cloud Gateway.
 *
 * Logger: 
 *   - Tool for logging messages, useful for debugging and monitoring.
 *
 * Mono<Void>: 
 *   - A Reactive Streams Publisher from Project Reactor. 
 *   - Used in Spring WebFlux for asynchronous operations.
 *   -  Mono<Void> specifically represents an operation that completes without emitting any item (used for side effects).
 *
 * filter Method: 
 *   - The key method where the filtering logic is defined. 
 *   - This method is called for every HTTP request passing through the Gateway.
 *  
 * ServerWebExchange:
 *   - Represents the HTTP request and response being processed by the Gateway
 *   - Allows access and modification to them.
 *  
 *  GatewayFilterChain: 
 *   - Represents the chain of filters through which the request is processed. 
 *   - Calling chain.filter(exchange) passes the request to the next filter in the chain
 *   or to the target service if no more filters are present.
 *
 */