package com.online.flight.booking.response;

import lombok.Data;

@Data
public class DisplayAirportResponse {

	private String airportCode;
	
	private String name;
	
	private String country;

	private String state;

	private String city;

	private String address;
}
