package com.online.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.flight.booking.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

}
