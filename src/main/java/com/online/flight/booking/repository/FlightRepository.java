package com.online.flight.booking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.online.flight.booking.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	
	@Query("SELECT a FROM Flight a " +
		       "WHERE (:flightNumber IS NULL OR a.flightNumber = :flightNumber) " +
		       "AND (:flightName IS NULL OR a.flightName = :flightName) " +
		       "AND (:pnr IS NULL OR a.pnr = :pnr)")
	Page<Flight> findAllByFilters(
			@Param("flightNumber")Integer flightNumber, 
			@Param("flightName")String flightName,
			@Param("pnr")String pnr, Pageable pageable);

	@Query("select a.name from Airport a")
	List<String> findByName();



}
