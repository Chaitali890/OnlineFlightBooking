package com.online.flight.booking.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DisplayManageFlightResponse {

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
