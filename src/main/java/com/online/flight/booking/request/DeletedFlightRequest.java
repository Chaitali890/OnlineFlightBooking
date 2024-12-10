package com.online.flight.booking.request;

import lombok.Data;

@Data
public class DeletedFlightRequest {

	private Integer id;
	
	private Boolean isDeleted;
}
