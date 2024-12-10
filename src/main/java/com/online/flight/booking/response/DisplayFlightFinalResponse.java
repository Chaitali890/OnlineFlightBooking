package com.online.flight.booking.response;

import java.util.List;

import lombok.Data;

@Data
public class DisplayFlightFinalResponse {

	   private int totalPages; // Total number of pages available
	    private long totalElements; // Total number of elements available
	    private int currentPage; // Current page number
	    private int pageSize; // Number of elements per page
	    
	    List<DisplayFlightResponse> response;
}
