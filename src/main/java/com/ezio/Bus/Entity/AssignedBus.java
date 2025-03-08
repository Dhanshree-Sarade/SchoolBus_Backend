package com.ezio.Bus.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AssignedBus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long busId;
    private String busNumber;
    private String busNumberPlate;
    private Long driverId;
    private String driverName;
    private String cleanerName;
    private String driverMobile;
    
    
	public AssignedBus() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AssignedBus(Long id, Long busId, String busNumber, String busNumberPlate, Long driverId, String driverName,
			String cleanerName, String driverMobile) {
		super();
		this.id = id;
		this.busId = busId;
		this.busNumber = busNumber;
		this.busNumberPlate = busNumberPlate;
		this.driverId = driverId;
		this.driverName = driverName;
		this.cleanerName = cleanerName;
		this.driverMobile = driverMobile;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getBusId() {
		return busId;
	}


	public void setBusId(Long busId) {
		this.busId = busId;
	}


	public String getBusNumber() {
		return busNumber;
	}


	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}


	public String getBusNumberPlate() {
		return busNumberPlate;
	}


	public void setBusNumberPlate(String busNumberPlate) {
		this.busNumberPlate = busNumberPlate;
	}


	public Long getDriverId() {
		return driverId;
	}


	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}


	public String getDriverName() {
		return driverName;
	}


	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}


	public String getCleanerName() {
		return cleanerName;
	}


	public void setCleanerName(String cleanerName) {
		this.cleanerName = cleanerName;
	}


	public String getDriverMobile() {
		return driverMobile;
	}


	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	
	
    
    
}
