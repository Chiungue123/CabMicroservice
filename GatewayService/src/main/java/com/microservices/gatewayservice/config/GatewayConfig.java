package com.microservices.gatewayservice.config;

import java.util.Arrays;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {

	@Bean
	RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes().route(p -> p.path("/bookings/**")
									   		.uri("lb://booking-service"))
							   .build();
	}
	
	@Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Set the allowed origins
        corsConfig.setMaxAge(3600L); // Set how long the response from a pre-flight request can be cached by clients
        corsConfig.addAllowedMethod("*"); // Allow all HTTP methods
        corsConfig.addAllowedHeader("*"); // Allow all headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Apply CORS configuration to all paths

        return new CorsWebFilter(source); // Create and return the CORS filter
    }
}

/*
 * KEY TERM DEFINITIONS
 * 
 * @Configuration:
 * 	A class-level annotation that indicates the class has @Bean definition methods. 
 * 	Spring uses these to configure and manage Spring beans.
 *
 * @Bean:
 * 	A method-level annotation that tells Spring to create a bean from the method and add it to the application context. 
 * 	The method’s return type defines the bean’s type, and the method name is typically used as the bean ID.
 * 
 * RouteLocator:
 * 	An interface in Spring Cloud Gateway that provides information about the routes in the application. It is used to define how requests are routed to various downstream services.
 * 
 * RouteLocatorBuilder:
 * 	A helper class used in Spring Cloud Gateway to build a RouteLocator. 
 * 	It provides a fluent API to define routes in a declarative manner.
 *
 */