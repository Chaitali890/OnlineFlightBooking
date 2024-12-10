package com.online.flight.booking.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.flight.booking.entity.Airport;
import com.online.flight.booking.entity.Flight;
import com.online.flight.booking.entity.Invoice;
import com.online.flight.booking.entity.ManageFlight;
import com.online.flight.booking.entity.Register;
import com.online.flight.booking.repository.AirportRepository;
import com.online.flight.booking.repository.FlightRepository;
import com.online.flight.booking.repository.InvoiceRepository;
import com.online.flight.booking.repository.ManageFlightRepository;
import com.online.flight.booking.repository.RegisterRepository;
import com.online.flight.booking.request.AddInvoiceRequest;
import com.online.flight.booking.request.AddRegisterRequest;
import com.online.flight.booking.request.AirportRequest;
import com.online.flight.booking.service.AirportService;
import com.online.flight.booking.service.FlightService;
import com.online.flight.booking.service.InvoiceService;
import com.online.flight.booking.service.RegisterService;

@Service
public class AirportServiceImpl implements AirportService, FlightService, InvoiceService, RegisterService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ManageFlightRepository manageFlightRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	public Optional<Invoice> addInvoiceToDatabase(AddInvoiceRequest request) {
	    Invoice invoice = null;

	    if (request.getId() != null) {
	        Optional<Invoice> invoices = invoiceRepository.findById(request.getId());
	        if (invoices.isPresent()) {
	            invoice = invoices.get();
	        } else {
	            return Optional.empty();
	        }
	    } else {
	        invoice = new Invoice();

	        // Fetch ManageFlight by ID if it's not null
	        if (request.getId() != null) {
	            Optional<ManageFlight> flights = manageFlightRepository.findById(request.getId());
	            if (flights.isPresent()) {
	                ManageFlight flight = flights.get();
	                invoice.setFlightName(flight.getFlightName());
	                invoice.setArrivalDate(flight.getArrivalDate());
	                invoice.setDepartureDate(flight.getDepartureDate());
	                invoice.setArrivalCity(flight.getArrivalCity());
	                invoice.setDepartureCity(flight.getDepartureCity());
	                invoice.setTicketPrice(flight.getTicketPrice());
	            }
	        }

	        // Fetch Flight by ID if it's not null
	        if (request.getId() != null) {
	            Optional<Flight> airportFlights = flightRepository.findById(request.getId());
	            if (airportFlights.isPresent()) {
	                Flight airportFlight = airportFlights.get();
	                invoice.setFlightNumber(airportFlight.getFlightNumber());
	            }
	        }

	        invoice.setNoOfPerson(request.getNoOfPerson());
	    }

	    Invoice savedInvoice = invoiceRepository.save(invoice);

	    if (request.getRequest() != null && !request.getRequest().isEmpty() && request.getNoOfPerson()!=null) {
	        List<Register> registers = new ArrayList<>();
	        int noOfPerson = Math.min(request.getNoOfPerson(), request.getRequest().size());
	        for(int i = 0; i<request.getNoOfPerson(); i++) {
	        	
	            AddRegisterRequest registerRequest1 = request.getRequest().get(i);

	            Register register = new Register();
	            register.setCity(registerRequest1.getCity());
	            register.setCountry(registerRequest1.getCountry());
	            register.setEmail(registerRequest1.getEmail());
	            register.setFirstName(registerRequest1.getFirstName());
	            register.setLastName(registerRequest1.getLastName());
	            registers.add(register);
	        }

	        registerRepository.saveAll(registers);
	    }

	    return Optional.of(savedInvoice);
	}

	
	public List<Airport> getAllAirports(){
		return airportRepository.findAll();
	}
	
	
	
	public Optional<Airport> saveAirportData(AirportRequest request){
		
		Airport airport = null;
		
		if(request.getId()!=null)
		{
			Optional<Airport> ports = airportRepository.findById(request.getId());
			if(ports.isPresent())
			{
				Airport port = ports.get();
			}
			else
			{
				return Optional.empty();
			}
		}
		else
		{
			if(airport == null)
				airport = new Airport();
			airport.setName(request.getName());
			airport.setCity(request.getCity());
			airport.setState(request.getState());
			airport.setAddress(request.getAddress());
			airport.setCountry(request.getCountry());
			airport.setPassengerCount(request.getPassengerCount());			
		}
			
		
		Airport savedAirports = airportRepository.save(airport);
		return Optional.ofNullable(savedAirports);
	}
	
	
	
	
	
	

	
//	public byte[] generateInvoiceReport(List<Airport> airport) {
//		
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		
//		try
//		{
//			Document document = new Document();
//			PdfWriter.getInstance(document, outputStream);
//			document.open();
//			
//			//Title
//			
//			Font fontTitle = new Font(Font.HELVETICA,18,Font.BOLD, new Color(0,102,204));
//			Paragraph title = new Paragraph("Airport Data",fontTitle);
//			title.setAlignment(Element.ALIGN_CENTER);
//			document.add(title);
//			document.add(Chunk.NEWLINE);
//			
//			
//			//Table
//			
//			PdfPTable table = new PdfPTable(6);
//			table.setWidthPercentage(100);
//			table.setSpacingBefore(10);
//			table.setSpacingAfter(10);
//			
//			
//			// Table Headers
//			
//			table.addCell("ID");
//			table.addCell("Name");
//			table.addCell("Country");
//			table.addCell("State");
//			table.addCell("City");
//			table.addCell("Address");
//			
//			
//			for(Airport port : airport)
//			{
//				table.addCell(port.getId().toString());
//				table.addCell(port.getName());
//				table.addCell(port.getCountry());
//				table.addCell(port.getState());
//				table.addCell(port.getCity());
//				table.addCell(port.getAddress());
//				
//			}
//			
//			
//			document.add(table);
//			document.close();
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		return outputStream.toByteArray();
//	}
	
	


public ByteArrayInputStream generateExcelReportUsingField() throws IOException {
   
	Optional<Airport> optionalAirport = airportRepository.findAll().stream().findFirst();
	
	if(optionalAirport.isEmpty())
	{
		throw new RuntimeException("No records found in the database");
	}
	
	Airport airport = optionalAirport.get();

    // Create a new workbook and a sheet
    Workbook workBook = new XSSFWorkbook();
    Sheet sheet = workBook.createSheet("Airports");

    // Define the fields to include in the report
    String[] selectedFields = {"name", "country", "state", "city", "address"};

    // Create the header row using field names
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < selectedFields.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(selectedFields[i]);
    }

    
        Row dataRow = sheet.createRow(1);
   
        for (int j = 0; j < selectedFields.length; j++) {
            try {
                Field field = Airport.class.getDeclaredField(selectedFields[j]);
                field.setAccessible(true);

                Object value = field.get(airport);
                Cell cell = dataRow.createCell(j);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Log and handle exceptions appropriately
                e.printStackTrace(); // Replace with proper logging in production
            }
        }

    // Write the workbook to a byte array stream
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    workBook.write(out);
    workBook.close();

    return new ByteArrayInputStream(out.toByteArray());
}









public ByteArrayInputStream generateExcelReportUsingFields() throws IOException {
    List<Airport> airports = airportRepository.findAll();

    // Create a new workbook and a sheet
    Workbook workBook = new XSSFWorkbook();
    Sheet sheet = workBook.createSheet("Airports");

    // Define the fields to include in the report
    String[] firstHeaderFields  = {"name", "address"};
    int[] firstHeaderIndices = {3,7};
    
    
    String[] secondHeaderFields = { "country", "state", "city"};
    int secondHeaderStartIndex = 4;
    
    
    
    // Define styles
    
    CellStyle headerStyle = workBook.createCellStyle();
    Font headerFont = workBook.createFont();
    headerFont.setColor(IndexedColors.WHITE.getIndex());
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);
    headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    headerStyle.setAlignment(HorizontalAlignment.CENTER);
    
    
    
    CellStyle dataStyle = workBook.createCellStyle();
    Font dataFont = workBook.createFont();
    dataFont.setColor(IndexedColors.WHITE.getIndex());
    dataStyle.setFont(dataFont);
    dataStyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
    dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
    
    

    // Create the header row using field names
    Row firstHeaderRow = sheet.createRow(0);
    for (int i = 0; i < firstHeaderFields.length; i++) {
        Cell cell = firstHeaderRow.createCell(firstHeaderIndices[i]);
        cell.setCellValue(firstHeaderFields[i].toUpperCase());
        cell.setCellStyle(headerStyle);
    }
        

    // Fill data rows
    int rowIndex = 1;
    for (Airport airport : airports) {
        Row dataRowFirst = sheet.createRow(rowIndex++);
        for (int i = 0; i < firstHeaderFields.length; i++) {
            try {
                Field field = Airport.class.getDeclaredField(firstHeaderFields[i]);
                field.setAccessible(true);

                Object value = field.get(airport);
                Cell cell = dataRowFirst.createCell(firstHeaderIndices[i]);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
                
                cell.setCellStyle(dataStyle);
                
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Log and handle exceptions appropriately
                e.printStackTrace(); // Replace with proper logging in production
            }
        }
    }
    
    
    Row secondHeaderRow = sheet.createRow(airports.size() +2);
    for(int i = 0; i< secondHeaderFields.length; i++)
    {
    	Cell cell = secondHeaderRow.createCell(secondHeaderStartIndex + i);
    	cell.setCellValue(secondHeaderFields[i].toUpperCase());
    	cell.setCellStyle(headerStyle);
    }
    
    
    
     rowIndex = airports.size() + 3;
     for(Airport airport : airports) {
    	 Row dataRowSecond = sheet.createRow(rowIndex++);
    	 for(int i = 0; i<secondHeaderFields.length; i++)
    	 {
    		 try {
    			 Field fields = Airport.class.getDeclaredField(secondHeaderFields[i]);
    			 fields.setAccessible(true);
    			 
    			 Object value = fields.get(airport);
    			 Cell cell = dataRowSecond.createCell(secondHeaderStartIndex + i);
    			 if(value !=null) {
    				 cell.setCellValue(value.toString());
    			 }
    			 
    			 cell.setCellStyle(dataStyle);
    		 	}
    		 catch(NoSuchFieldException | IllegalAccessException e) {
    			 e.printStackTrace();
    		 }
    		 
    	 }
     }
     
     
     
     
     for(int index : firstHeaderIndices) {
    	 sheet.autoSizeColumn(index);
     }
     
	 for(int i = 0; i<secondHeaderFields.length; i++)
	 {
		 sheet.autoSizeColumn(secondHeaderStartIndex +i);
	 }
     
     
    // Write the workbook to a byte array stream
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    workBook.write(out);
    workBook.close();

    return new ByteArrayInputStream(out.toByteArray());
}


public  void importExcelData(MultipartFile file) throws EncryptedDocumentException, IOException {
	try(InputStream inputstream = file.getInputStream()){
		Workbook workBook = WorkbookFactory.create(inputstream);
		Sheet sheet = workBook.getSheetAt(0);
		
		List<Airport> airports = new ArrayList<>();
		
		for(Row row : sheet)
		{
			if(row.getRowNum() == 0) 
				continue;
			Airport port = new Airport();
			
			Cell nameCell = row.getCell(0);
			if(nameCell!=null)
			{
				port.setName(nameCell.getStringCellValue());
			}
			
			Cell countryCell = row.getCell(1);
			if(countryCell!=null)
			{
			port.setCountry(countryCell.getStringCellValue());
			}
			
			
			Cell stateCell = row.getCell(2);
			if(stateCell!=null)
			{
			port.setState(stateCell.getStringCellValue());
			}
		
			Cell cityCell = row.getCell(3);
			if(cityCell!=null)
			{
				port.setCity(cityCell.getStringCellValue());
			}
			
			Cell addressCell = row.getCell(4);
			if(addressCell!=null)
			{
				port.setAddress(addressCell.getStringCellValue());
			}
			
			airports.add(port);
		}
		
		 airportRepository.saveAll(airports);
		
	}catch(Exception e)
	{
		throw new RuntimeException("Failed to pass excel file:" + e.getMessage(), e);
	}

}
}
