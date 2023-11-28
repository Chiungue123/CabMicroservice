package com.microservices.gatewayservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.gatewayservice.dto.BookingsDTO;
import com.microservices.gatewayservice.proxy.BookingsClient;

@RestController
@RequestMapping("/gateway/bookings")
public class BookingController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final BookingsClient bookingsClient;

    public BookingController(BookingsClient bookingsClient) {
        this.bookingsClient = bookingsClient;
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<BookingsDTO> getBookingById(@PathVariable Integer id) {
		
		logger.debug("Booking Gateway: Finding Cab Booking with Id: {}", id);
		BookingsDTO booking = bookingsClient.getBookingById(id);
		return ResponseEntity.ok(booking);
	}

	@GetMapping()
	public ResponseEntity<List<BookingsDTO>> getAllBookings() {
		
	    logger.debug("Booking Gateway: Getting All Bookings");
		List<BookingsDTO> bookings = bookingsClient.getBookings();
		return ResponseEntity.ok(bookings);
	}
	
	@PostMapping("/create")
	public ResponseEntity<BookingsDTO> createBooking(@RequestBody BookingsDTO book) {
		
		logger.debug("Booking Gateway: Creating Book: {}", book);
		BookingsDTO booking = bookingsClient.createBooking(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	}
	
	@PutMapping("/update")
	public ResponseEntity<BookingsDTO> updateBooking(@RequestBody BookingsDTO book) {
		
		logger.debug("Booking Gateway: Updating Book: {}", book);
		BookingsDTO booking = bookingsClient.updateBooking(book);
		return ResponseEntity.ok(booking);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BookingsDTO> deleteBooking(@PathVariable Integer id) {
		
		logger.debug("Booking Gateway: Deleting Book with Id: {}", id);
		BookingsDTO booking = bookingsClient.deleteBookingById(id);
		return ResponseEntity.ok(booking);
	}
}