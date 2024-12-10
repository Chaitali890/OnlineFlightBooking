package com.online.flight.booking.request;

import java.util.List;

import lombok.Data;

@Data
public class DeletedAirportRequest {

	private Integer id;
	private Boolean isDeleted;
}
