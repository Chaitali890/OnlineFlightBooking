package com.online.flight.booking.request;
import lombok.Data;

@Data
public class AirportRequest {

    private Integer id;
	
	private String name;
	
	private String country;

	private String state;

	private String city;

	private String address;
	
	private int passengerCount;

}
