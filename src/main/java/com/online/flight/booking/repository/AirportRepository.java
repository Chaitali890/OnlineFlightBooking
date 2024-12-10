package com.online.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import com.online.flight.booking.entity.Airport;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

		
	@Query("SELECT a FROM Airport a " +
		       "WHERE (:city IS NULL OR a.city = :city) " +
		       "AND (:name IS NULL OR a.name = :name) " +
		       "AND (:airportCode IS NULL OR a.airportCode = :airportCode)")
		Page<Airport> findAllByFilters(@Param("city") String city, @Param("name") String name, @Param("airportCode") String airportCode, Pageable pageable);

	
	




}
