package com.microservices.bookingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class BookingServiceApplicationTests {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	void contextLoads() {
		logger.debug("@Test: contextLoads()");
	}

}
