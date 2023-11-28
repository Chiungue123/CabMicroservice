package com.microservices.gatewayservice.dto;

public class BookingsDTO {

	private Integer id;
	private String pickUpLocation;
	private String dropOffLocation;
	private String pickUpTime;
	private String fare;
	private String vehicleType;
	private String bookingEnvironment;
	
	BookingsDTO(){
		
	}
	
	public BookingsDTO(Integer id, String pickUpLocation, String dropOffLocation, String pickUpTime, 
					   String fare, String vehicleType, String bookingEnvironment) {
		this.id = id;
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.pickUpTime = pickUpTime;
		this.fare = fare;
		this.vehicleType = vehicleType;
		this.bookingEnvironment = bookingEnvironment;
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
	public String getBookingEnvironment() {
		return bookingEnvironment;
	}
	public void setBookingEnvironment(String bookingEnvironment) {
		this.bookingEnvironment = bookingEnvironment;
	}
	
	
}
