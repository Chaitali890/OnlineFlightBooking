package com.online.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.flight.booking.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Integer> {

}
