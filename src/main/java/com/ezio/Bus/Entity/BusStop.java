package com.ezio.Bus.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BusStop {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stopName;  // Only storing stop names

    @ManyToOne
    @JoinColumn(name = "bus_route_id")
    @JsonBackReference
    private BusRoute busRoute;

	public BusStop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusStop(Long id, String stopName, BusRoute busRoute) {
		super();
		this.id = id;
		this.stopName = stopName;
		this.busRoute = busRoute;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStopName() {
		return stopName;
	}

	public void setStopName(String stopName) {
		this.stopName = stopName;
	}

	public BusRoute getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}
    
    
}
