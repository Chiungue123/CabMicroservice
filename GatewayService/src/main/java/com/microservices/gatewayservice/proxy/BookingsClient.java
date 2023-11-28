package com.microservices.gatewayservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.gatewayservice.dto.BookingsDTO;

@FeignClient(name = "booking-service")
public interface BookingsClient {

	    @GetMapping("/bookings/{id}")
	    BookingsDTO getBookingById(@PathVariable("id") Integer id);
	   
	    @GetMapping("/bookings")
	    List<BookingsDTO> getBookings();
	    
	    @PostMapping("/create")
	    BookingsDTO createBooking(@RequestBody BookingsDTO booking);
	    
	    @PutMapping("/update")
	    BookingsDTO updateBooking(@RequestBody BookingsDTO booking);
	    
	    /*
	     *  Will Require validation findByIdNotUsername in Booking Service's Service Package
	     */
	   
	    @DeleteMapping("/delete/{id}")
	    BookingsDTO deleteBookingById(@PathVariable("id") Integer id);
	
}
