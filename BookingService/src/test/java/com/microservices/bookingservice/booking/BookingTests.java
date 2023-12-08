package com.microservices.bookingservice.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.bookingservice.dto.BookingTestDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingTests {

	@Autowired
	private MockMvc mockMvc;

	final static Logger logger = LoggerFactory.getLogger(BookingTests.class);

	@Test
	void createBookingValidData1() throws Exception {
		BookingTestDTO booking = new BookingTestDTO("Location A", "Location B", "10:30", "25.50", "Ford", 200);
		
		mockMvc.perform(post("/bookings/create")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(booking)))
	            .andExpect(status().is(booking.getExpectedStatus()));
		
	}
	
	@Test
	void createBookingValidData2() throws Exception {
		BookingTestDTO booking = new BookingTestDTO("Location H", "Location T", "18:30", "25.50", "Audi", 200);
		
		mockMvc.perform(post("/bookings/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(booking)))
				.andExpect(status().is(booking.getExpectedStatus()));
		
	}
	
	@Test
	void createBookingWithInvalidData() throws Exception {
	    BookingTestDTO invalidBooking = new BookingTestDTO("", "Location B", "07:30", "25.50", "Bently", 400);

	    // Assert that a RuntimeException is thrown
	    assertThrows(Exception.class, () -> {
	        mockMvc.perform(post("/bookings/create")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(invalidBooking)))
	            .andExpect(status().is(400));
	    });
	}
	
	@Test
	void createBookingInvalidData2() throws Exception {
		BookingTestDTO invalidBooking = new BookingTestDTO("Location A", "", "20:00", "25.50", "Honda", 400);
		
		 // Assert that a RuntimeException is thrown
	    assertThrows(Exception.class, () -> {
	        mockMvc.perform(post("/bookings/create")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(invalidBooking)))
	            .andExpect(status().is(400));
	    });
	}
	
	@Test
    void updateBookingValidData1() throws Exception {
		BookingTestDTO booking = new BookingTestDTO("Location Y", "Location Z", "09:30", "29.70", "Honda", 200);
		
		mockMvc.perform(put("/bookings/update/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(booking)))
				.andExpect(status().is(booking.getExpectedStatus()));
    }
	
	@Test
	void updateBookingValudData2() throws Exception {
		BookingTestDTO booking = new BookingTestDTO("Location P", "Location G", "19:00", "39.10", "Bently", 200);
		
		mockMvc.perform(put("/bookings/update/{id}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(booking)))
				.andExpect(status().is(booking.getExpectedStatus()));
	}
	
}
