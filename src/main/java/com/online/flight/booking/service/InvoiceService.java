package com.online.flight.booking.service;

import java.util.Optional;

import com.online.flight.booking.entity.Invoice;
import com.online.flight.booking.request.AddInvoiceRequest;

public interface InvoiceService {

	public Optional<Invoice> addInvoiceToDatabase(AddInvoiceRequest request);

}
