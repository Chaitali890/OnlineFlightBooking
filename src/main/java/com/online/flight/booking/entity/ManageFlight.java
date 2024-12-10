package com.online.flight.booking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manageFlight")
public class ManageFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String arrivalAirportName;
	
	private String departureAirportName;
	
	private String flightName;
	
	private String arrivalCity;
	
	private String departureCity;
	
	private LocalDate arrivalDate;
	
	private LocalDate departureDate;
	
	private String arrivalTime;
	
	private String departureTime;
	
	private Long ticketPrice;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getArrivalAirportName() {
		return arrivalAirportName;
	}



	public void setArrivalAirportName(String arrivalAirportName) {
		this.arrivalAirportName = arrivalAirportName;
	}



	public String getDepartureAirportName() {
		return departureAirportName;
	}



	public void setDepartureAirportName(String departureAirportName) {
		this.departureAirportName = departureAirportName;
	}



	public String getFlightName() {
		return flightName;
	}



	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}



	public String getArrivalCity() {
		return arrivalCity;
	}



	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}



	public String getDepartureCity() {
		return departureCity;
	}



	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}



	public LocalDate getArrivalDate() {
		return arrivalDate;
	}



	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}



	public LocalDate getDepartureDate() {
		return departureDate;
	}



	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}



	public String getArrivalTime() {
		return arrivalTime;
	}



	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	public String getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}


	public Long getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public ManageFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManageFlight(Integer id, String arrivalAirportName, String departureAirportName, String flightName,
			String arrivalCity, String departureCity, LocalDate arrivalDate, LocalDate departureDate,
			String arrivalTime, String departureTime, Long ticketPrice) {
		super();
		this.id = id;
		this.arrivalAirportName = arrivalAirportName;
		this.departureAirportName = departureAirportName;
		this.flightName = flightName;
		this.arrivalCity = arrivalCity;
		this.departureCity = departureCity;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.ticketPrice = ticketPrice;
	}
	
	

}
