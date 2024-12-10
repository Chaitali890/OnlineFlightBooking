package com.online.flight.booking.response;

import lombok.Data;

@Data
public class DeletedFlightResponse {

	private Integer id;
	
	private Boolean isDeleted;
}
