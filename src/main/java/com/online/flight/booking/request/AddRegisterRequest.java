package com.online.flight.booking.request;

import lombok.Data;

@Data
public class AddRegisterRequest {

private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String confirmPassword;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String email;
	
	private Long mobileNo;
}
