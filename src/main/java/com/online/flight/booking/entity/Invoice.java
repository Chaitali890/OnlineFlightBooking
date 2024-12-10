package com.online.flight.booking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer noOfPerson;
	
	private Integer flightNumber;
	
	private String flightName;
	
	private LocalDate arrivalDate;
	
	private LocalDate departureDate;
	
	private String arrivalCity;
	
	private String departureCity;

	private Long ticketPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNoOfPerson() {
		return noOfPerson;
	}

	public void setNoOfPerson(Integer noOfPerson) {
		this.noOfPerson = noOfPerson;
	}

	public Integer getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
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

	public Long getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	

	public Invoice(Integer id, Integer noOfPerson, Integer flightNumber,
			String flightName, LocalDate arrivalDate, LocalDate departureDate, String arrivalCity, String departureCity,
			Long ticketPrice) {
		super();
		this.id = id;
		this.noOfPerson = noOfPerson;
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.arrivalCity = arrivalCity;
		this.departureCity = departureCity;
		this.ticketPrice = ticketPrice;
	}

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
