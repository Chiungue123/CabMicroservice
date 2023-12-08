package com.microservices.bookingservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bookingservice.jpa.Booking;
import com.microservices.bookingservice.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService service;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Integer id) {
    	
    	logger.debug("Booking Controller: Getting Booking with id: {}", id);
    	Booking booking = service.getBookingById(id);
    	
    	return booking;
    }
    
    @GetMapping()
    List<Booking> getAllBookings(){
    	
    	logger.debug("Booking Controller: Getting All Bookings");
    	List<Booking> bookings = service.getAllBookings();
    	
    	return bookings;
    }
    
    @PostMapping("/create")
    Booking createBooking(@RequestBody Booking booking) {
    	
    	logger.debug("Booking Controller: Creating Booking: {}", booking);
    	Booking newBooking = service.createBooking(booking);
    	
    	return newBooking;
    }
    
    @GetMapping("/payment/{pickUpTime}/{vehicleType}")
    String calculatePayment(@PathVariable("pickUpTime") String pickUpTime, @PathVariable("vehicleType") String vehicleType) {
    	
    	logger.debug("Booking Controller: Calculating Payment for Booking {}");
    	String calculatedBooking = service.calculatePayment(pickUpTime, vehicleType);
    	
    	return calculatedBooking;
    }
    
    @PutMapping("/update/{id}")
    Booking updateBooking(@PathVariable Integer id, @RequestBody Booking booking) {
    	
    	logger.debug("Booking Controller: Updating Booking with id: {}", id);
    	Booking updatedBooking = service.updateBooking(id, booking);
    	
    	return updatedBooking;
    }
   
    @DeleteMapping("/delete/{id}")
    void deleteBookingById(@PathVariable("id") Integer id) {
    	
    	logger.debug("Booking Controller: Deleting Booking with id: {}", id);
    	service.deleteBookingById(id);
    	
    }
	
}
