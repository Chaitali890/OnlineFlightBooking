package com.online.flight.booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.flight.booking.entity.ManageFlight;

public interface ManageFlightRepository extends JpaRepository<ManageFlight, Integer> {

	@Query(value="select * from manage_flight where arrival_city = :arrivalCity AND departure_city = :departureCity",nativeQuery = true)
	Page<ManageFlight> findByArrivalCityAndDepartureCity(
			@Param("arrivalCity") String arrivalCity, 
			@Param("departureCity") String departureCity, Pageable pageable);

}
