package com.online.flight.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer flightNumber;
	
	private String flightName;
	
	private String pnr;
	
	@Column(name = "active", columnDefinition = "boolean default 1")
	private boolean active = true;
	
	@Column(name = "is_deleted", columnDefinition  = "boolean default 0")
	private boolean isDeleted = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Flight(Integer id, Integer flightNumber, String flightName, String pnr, boolean active, boolean isDeleted) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.pnr = pnr;
		this.active = active;
		this.isDeleted = isDeleted;
	}

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}