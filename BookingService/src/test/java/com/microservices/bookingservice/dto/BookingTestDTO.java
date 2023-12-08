package com.microservices.bookingservice.dto;

public class BookingTestDTO {

	private String pickUpLocation;
	private String dropOffLocation;
	private String pickUpTime; // Time Format 24:00
	private String fare;
	private String vehicleType;
	private int expectedStatus;
	
	BookingTestDTO() {}

	public BookingTestDTO(String pickUpLocation, String dropOffLocation, String pickUpTime, String fare,
			String vehicleType, int expectedStatus) {
		
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.pickUpTime = pickUpTime;
		this.fare = fare;
		this.vehicleType = vehicleType;
		this.expectedStatus = expectedStatus;
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

	public int getExpectedStatus() {
		return expectedStatus;
	}

	public void setExpectedStatus(int expectedStatus) {
		this.expectedStatus = expectedStatus;
	}
}
