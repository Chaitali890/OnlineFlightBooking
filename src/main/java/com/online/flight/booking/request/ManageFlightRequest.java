package com.online.flight.booking.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ManageFlightRequest {

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
}
