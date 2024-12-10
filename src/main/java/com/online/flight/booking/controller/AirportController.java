package com.online.flight.booking.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;


import com.online.flight.booking.entity.Airport;
import com.online.flight.booking.entity.Flight;
import com.online.flight.booking.entity.Invoice;
import com.online.flight.booking.entity.ManageFlight;
import com.online.flight.booking.entity.Register;
import com.online.flight.booking.repository.AirportRepository;
import com.online.flight.booking.request.AddFlightRequest;
import com.online.flight.booking.request.AddInvoiceRequest;
import com.online.flight.booking.request.AddRegisterRequest;
import com.online.flight.booking.request.AddSearchRequest;
import com.online.flight.booking.request.AirportRequest;
import com.online.flight.booking.request.DeletedAirportRequest;
import com.online.flight.booking.request.DeletedFlightRequest;
import com.online.flight.booking.request.ManageFlightRequest;
import com.online.flight.booking.response.DeletedAirportResponse;
import com.online.flight.booking.response.DeletedFlightResponse;
import com.online.flight.booking.response.DisplayAirportFinalResponse;
import com.online.flight.booking.response.DisplayFlightFinalResponse;
import com.online.flight.booking.response.DisplayManageFlightFinalResponse;
import com.online.flight.booking.response.DisplayManageFlightResponse;
import com.online.flight.booking.service.AirportPdfService;
import com.online.flight.booking.service.AirportService;
import com.online.flight.booking.service.FlightService;
import com.online.flight.booking.service.InvoiceService;
import com.online.flight.booking.service.RegisterService;
import com.online.flight.booking.serviceImpl.AirportPdfserviceImpl;
import com.online.flight.booking.serviceImpl.AirportServiceImpl;

@RestController
@RequestMapping("/airport")
public class AirportController {
	
	@Autowired
	private AirportService airPortService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private AirportPdfService airportPdfService;
	
	
	@PostMapping("/invoice")
	public ResponseEntity<Optional<Invoice>> saveInvoiceToDB(@RequestBody AddInvoiceRequest request){
		
		Optional<Invoice> response = invoiceService.addInvoiceToDatabase(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/addAirport")
	public ResponseEntity<Optional<Airport>> saveAirportDetails(@RequestBody AirportRequest request)
	{
		Optional<Airport> savedPort = airPortService.saveAirportData(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPort);
	}
//	
	
	@PostMapping("/add-airports")
	public ResponseEntity<List<Airport>> addAirPorts(@RequestBody List<AirportRequest> request)
	{
	
	List<Airport> airPortList = airPortService.saveAirportData(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(airPortList);
	
	}
//	
//	
	@GetMapping("/getAllAirportData")
	public ResponseEntity<DisplayAirportFinalResponse> displayAirportData(
	        @RequestParam(value = "city", required = false) String city,
	        @RequestParam(value = "state", required = false) String state,
	        @RequestParam(value = "airportCode", required = false) String airportCode, Pageable page) {
		
		  // Trim the parameters to remove leading or trailing spaces
	    city = city != null ? city.trim() : null;
	    state = state != null ? state.trim() : null;
	    airportCode = airportCode != null ? airportCode.trim() : null;

	    DisplayAirportFinalResponse response = airPortService.displayAirportData(city, state, airportCode,page);

	    if (response != null && response.getResponse() != null && !response.getResponse().isEmpty()) {
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DisplayAirportFinalResponse());
	    }
	}
//
//	
	@PostMapping("/delete")
	public ResponseEntity<List<DeletedAirportResponse>> deleteFromAirport(@RequestBody List<DeletedAirportRequest> request){
		
		List<DeletedAirportResponse> response = airPortService.deleteAirport(request);
		
		if(response!=null)
		{
			return ResponseEntity.ok(response);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<DeletedAirportResponse>());
		}

	}	
	
	@PostMapping("/addFlight")
	public ResponseEntity<List<Flight>> saveFlightTo(@RequestBody List<AddFlightRequest> request){
		
		List<Flight> response = flightService.addFlights(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@GetMapping("/getFlights")
	public ResponseEntity<DisplayFlightFinalResponse> displayFlightData(
	        @RequestParam(value = "flightNumber", required = false) Integer flightNumber,
	        @RequestParam(value = "flightName", required = false) String flightName,
	        @RequestParam(value = "pnr", required = false) String pnr, Pageable page)
	{
	  // Trim the parameters to remove leading or trailing spaces
	flightNumber = flightNumber != null ? flightNumber: null;
	flightName = flightName != null ? flightName.trim() : null;
	pnr = pnr != null ? pnr.trim() : null;
	
		DisplayFlightFinalResponse response = flightService.displayFlight(flightNumber, flightName,pnr,page);
		if(response!=null || !response.getResponse().isEmpty())
		{
			return ResponseEntity.ok(response);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DisplayFlightFinalResponse());
		}
		
	}
	
//	
//	
	
	@PostMapping("/deleteFlight")
	public ResponseEntity<List<DeletedFlightResponse>> deleteFromFlight(@RequestBody List<DeletedFlightRequest> request){
		
		List<DeletedFlightResponse> response = flightService.deleteFlight(request);
		
		if(response!=null)
		{
			return ResponseEntity.ok(response);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<DeletedFlightResponse>());
		}
	}
	
	
	@GetMapping("/getAirportNames")
	public ResponseEntity<List<String>> airPortNamesList()
	{
		List<String> response = airPortService.displayAirportName();
		if(response!=null)
		{ 
			return ResponseEntity.ok(response);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<String>());
		}
	}
	
	
	
	@PostMapping("/manageFlight")
	public ResponseEntity<Optional<ManageFlight>> manageFlightDetails(@RequestBody ManageFlightRequest request)
	{
		Optional<ManageFlight> manageFlight  = airPortService.manageFlights(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(manageFlight);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<List<Register>> saveRegistration(@RequestBody List<AddRegisterRequest> request){
		
		List<Register> register = registerService.makeRegister(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(register);
	}
//	
//	
	@PostMapping("/search")
	public ResponseEntity<DisplayManageFlightFinalResponse> displayManageFlightList(@RequestBody AddSearchRequest request,Pageable page){
		
		DisplayManageFlightFinalResponse finalResponse = flightService.displayFlightDetails(request, page);
		if(finalResponse!=null && !finalResponse.getFinalResponse().isEmpty())
		{
			return ResponseEntity.ok(finalResponse);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DisplayManageFlightFinalResponse());
		}
		
	}
	
	
	@PostMapping("/search/byId")
	public ResponseEntity<List<DisplayManageFlightResponse>> displayManageFlightListById(@RequestBody AddSearchRequest request){
		
		List<DisplayManageFlightResponse> finalResponse = flightService.displayFlightDetailsById(request);
		if(finalResponse!=null)
		{
			return ResponseEntity.ok(finalResponse);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<DisplayManageFlightResponse>());
		}
		
	}
	
	
	
	
	

	@GetMapping("/report")
	public ResponseEntity<byte[]> getAirportReport(){
		List<Airport> airPorts = airPortService.getAllAirports();
		byte[] pdf = airportPdfService.generateInvoiceReport(airPorts);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = airportData.pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.body(pdf);
				
	}
	
	
	
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportEmployeesToExcel(){
		
		
		try
		{
			ByteArrayInputStream excelStream = airPortService.generateExcelReportUsingFields();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename=Airport.xlsx");
			
			return ResponseEntity.ok()
						.headers(headers)
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(new InputStreamResource(excelStream));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
	
	
	
	@GetMapping("/exports")
	public ResponseEntity<InputStreamResource> exportEmployeeToExcel(){
		
		
		try
		{
			ByteArrayInputStream excelStream = airPortService.generateExcelReportUsingField();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename=Airport.xlsx");
			
			return ResponseEntity.ok()
						.headers(headers)
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(new InputStreamResource(excelStream));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
	
	
	@PostMapping("/import")
	public ResponseEntity<String> importEmployees(@RequestParam("file") MultipartFile file){
		if(file.isEmpty())
		{
			return ResponseEntity.badRequest().body("please upload a valid excel file");
		}
		try
		{
			airPortService.importExcelData(file);
			return ResponseEntity.ok("Data imported successfully");		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Failed to import data:"+e.getMessage());
		}
	}
	
	
	@GetMapping("generate-pdf")
	public ResponseEntity<byte[]> generatePdf(){
		try
		{
			List<Airport> airport = airPortService.getAllAirports();
			String fileName = "AirportData.pdf";
			
			//Generate the Pdf
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			AirportPdfserviceImpl.generateAirportreport(outputStream, airport);
			
			//Return the PDF as response
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", fileName);
			
			return ResponseEntity.ok()
					.headers(headers)
					.body(outputStream.toByteArray());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
}