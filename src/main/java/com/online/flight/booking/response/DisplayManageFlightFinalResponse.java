package com.online.flight.booking.response;

import java.util.List;

import lombok.Data;

@Data
public class DisplayManageFlightFinalResponse {
	
	  private int totalPages; // Total number of pages available
	    private long totalElements; // Total number of elements available
	    private int currentPage; // Current page number
	    private int pageSize; // Number of elements per page

	private List<DisplayManageFlightResponse> finalResponse;
}
