package com.microservices.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.bookingservice.jpa.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
