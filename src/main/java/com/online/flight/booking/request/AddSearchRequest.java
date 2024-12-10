package com.online.flight.booking.request;

import lombok.Data;

@Data
public class AddSearchRequest {

	private String arrivalCity;
	
	private String departureCity;
	
	private Integer id;
}
