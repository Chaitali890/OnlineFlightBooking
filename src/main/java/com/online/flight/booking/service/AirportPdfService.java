package com.online.flight.booking.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.web.multipart.MultipartFile;

import com.online.flight.booking.entity.Airport;
import com.online.flight.booking.request.AirportRequest;

public interface AirportPdfService {

	public byte[] generateInvoiceReport(List<Airport> airport);


}
