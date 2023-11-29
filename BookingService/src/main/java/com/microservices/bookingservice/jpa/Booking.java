package com.microservices.bookingservice.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BOOKING")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Integer id;
	
	@Column(name="pickup_location")
	String pickUpLocation;
	
	@Column(name="dropoff_location")
	String dropOffLocation;
	
	@Column(name="pickup_time")
	String pickUpTime; // Time Format 24:00
	
	@Column(name="fare")
	String fare;
	
	@Column(name="vehicle_type")
	String vehicleType;
	
	public Booking() {
	
	}

	public Booking(Integer id, String pickUpLocation, 
			String dropOffLocation, String pickUpTime, String fare,
			String vehicleType) {
		this.id = id;
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.pickUpTime = pickUpTime;
		this.fare = fare;
		this.vehicleType = vehicleType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPickUpLocation() {
		return pickUpLocation;
	}

	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	public String getDropOffLocation() {
		return dropOffLocation;
	}

	public void setDropOffLocation(String dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}

	public String getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}
