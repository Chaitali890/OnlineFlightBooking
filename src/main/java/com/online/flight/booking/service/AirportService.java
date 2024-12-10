package com.online.flight.booking.service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.EncryptedDocumentException;
import org.springframework.data.domain.Page;

import com.online.flight.booking.entity.Airport;
import com.online.flight.booking.entity.Flight;
import com.online.flight.booking.entity.ManageFlight;
import com.online.flight.booking.request.AddFlightRequest;
import com.online.flight.booking.request.AirportRequest;
import com.online.flight.booking.request.DeletedAirportRequest;
import com.online.flight.booking.request.DeletedFlightRequest;
import com.online.flight.booking.request.ManageFlightRequest;
import com.online.flight.booking.response.DeletedAirportResponse;
import com.online.flight.booking.response.DeletedFlightResponse;
import com.online.flight.booking.response.DisplayAirportFinalResponse;
import com.online.flight.booking.response.DisplayFlightFinalResponse;

public interface AirportService {
//
//	public Optional<Airport> saveAirport(AirportRequest request);
//	
	public Optional<Airport> saveAirportData(AirportRequest request);
	
	public List<Airport> getAllAirports();
		
	public ByteArrayInputStream generateExcelReportUsingFields() throws IOException;

	public  void importExcelData(MultipartFile file) throws EncryptedDocumentException, IOException;

	public ByteArrayInputStream generateExcelReportUsingField() throws IOException;
	

//
//	public DisplayAirportFinalResponse displayAirportData(String city, String name, String airportCode,Pageable page);
//		
//	public List<DeletedAirportResponse> deleteAirport(List<DeletedAirportRequest> request);
//
//	public List<String> displayAirportName();
//
//	public Optional<ManageFlight> manageFlights(ManageFlightRequest request);

}
