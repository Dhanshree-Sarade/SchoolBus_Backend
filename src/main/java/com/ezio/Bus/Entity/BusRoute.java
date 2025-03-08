package com.ezio.Bus.Entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BusRoute {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String busNumber;
    private String busNumberPlate;

    public String getBusNumberPlate() {
		return busNumberPlate;
	}


	public void setBusNumberPlate(String busNumberPlate) {
		this.busNumberPlate = busNumberPlate;
	}


	@OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BusStop> stops = new ArrayList<>();
    
    
	public BusRoute() {
	super();
	// TODO Auto-generated constructor stub
}


	public BusRoute(String routeName, List<BusStop> stops) {
        this.routeName = routeName;
        this.stops = stops;
        for (BusStop stop : stops) {
            stop.setBusRoute(this);
        }
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRouteName() {
		return routeName;
	}


	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}


	public List<BusStop> getStops() {
		return stops;
	}


	public void setStops(List<BusStop> stops) {
		this.stops = stops;
	}


	public String getBusNumber() {
		return busNumber;
	}


	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
   
	
	
}
