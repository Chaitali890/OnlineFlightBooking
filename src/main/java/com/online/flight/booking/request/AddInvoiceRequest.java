package com.online.flight.booking.request;

import java.util.List;

import lombok.Data;

@Data
public class AddInvoiceRequest {
	
	private Integer id;

	private Integer noOfPerson;
	
	private List<AddRegisterRequest> request;

}
