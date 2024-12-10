package com.online.flight.booking.request;

import lombok.Data;

@Data
public class AddFlightRequest {

	private Integer id;
	
	private Integer flightNumber;
	
	private String flightName;
	
	private String pnr;

}
