package com.microservices.bookingservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.bookingservice.jpa.Booking;
import com.microservices.bookingservice.proxy.PaymentProxy;
import com.microservices.bookingservice.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingRepository repository;
	
	@Autowired
	PaymentProxy paymentProxy;

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Booking getBookingById(Integer id) {
		
		logger.debug("Booking Service: Getting Booking with id: {}", id);
		Booking booking = repository.findById(id).orElse(null);
		
		return booking;
	}
	
	public List<Booking> getAllBookings() {
		
		logger.debug("Booking Service: Getting All Bookings");
		List<Booking> bookings = repository.findAll();
		
		return bookings;
	}

	public Booking createBooking(Booking booking) {
		
		logger.debug("Booking Service: Create Booking: {}", booking);
		this.validateBooking(booking);
		
		return null;
	}
	
	public Booking updateBooking(Integer id, Booking booking) {
		
		logger.debug("Booking Service: Updating Booking id: {}", id, "to Booking: {}", booking);
		this.validateBooking(booking);
		
		repository.findById(id)
			.ifPresent( b -> {
				b.setPickUpLocation(booking.getPickUpLocation());
				b.setDropOffLocation(booking.getDropOffLocation());
				b.setPickUpTime(booking.getPickUpTime());
				b.setFare(booking.getFare());
				b.setVehicleType(booking.getVehicleType());
				
			repository.save(b);
		});
		
		return booking;
	}

	public void deleteBookingById(Integer id) {
	
		logger.debug("Booking Service: Deleting Booking with id: {}", id);
		
		repository.deleteById(id);
		
	}

	public Booking calculatePayment(Booking booking) {
		
		logger.debug("Booking Service: Calculating Payment for Booking: {}", booking);
		
		this.validateBooking(booking);
		Booking newBooking = this.paymentProxy.calculatePayment(booking);
		
		return newBooking;
	}
	
	private void validateBooking(Booking booking) {
       
		if (booking.getPickUpLocation().isEmpty() ||
            booking.getDropOffLocation().isEmpty() ||
            booking.getPickUpTime().isEmpty() ||
			booking.getFare().isEmpty() ||
			booking.getVehicleType().isEmpty()) {
			
            throw new RuntimeException("Invalid Booking Data: " + booking);
        }
	}
}