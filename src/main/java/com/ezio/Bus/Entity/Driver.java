package com.ezio.Bus.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long did;
	
	private String driverFirstName;
	private String driverLastName;
	private String driverEmail;
	private String driverMob;
	private String driverPassword;
	
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Driver(Long did, String driverFirstName, String driverLastName, String driverEmail, String driverMob,
			String driverPassword) {
		super();
		this.did = did;
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.driverEmail = driverEmail;
		this.driverMob = driverMob;
		this.driverPassword = driverPassword;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getDriverFirstName() {
		return driverFirstName;
	}

	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}

	public String getDriverLastName() {
		return driverLastName;
	}

	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}

	public String getDriverEmail() {
		return driverEmail;
	}

	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}

	public String getDriverMob() {
		return driverMob;
	}

	public void setDriverMob(String driverMob) {
		this.driverMob = driverMob;
	}

	public String getDriverPassword() {
		return driverPassword;
	}

	public void setDriverPassword(String driverPassword) {
		this.driverPassword = driverPassword;
	}
	
	
	
	
}
