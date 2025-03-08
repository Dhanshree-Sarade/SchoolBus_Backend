package com.ezio.Bus.Entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReportedIssue {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String issueType;
    private String description;
    private String driverEmail;
    private Long driverId;
    private String busRoute;

    @ElementCollection // This allows storing a list as a separate table
    private List<String> studentsEmails;

	public ReportedIssue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportedIssue(Long id, String issueType, String description, String driverEmail, Long driverId,
			String busRoute, List<String> studentsEmails) {
		super();
		this.id = id;
		this.issueType = issueType;
		this.description = description;
		this.driverEmail = driverEmail;
		this.driverId = driverId;
		this.busRoute = busRoute;
		this.studentsEmails = studentsEmails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDriverEmail() {
		return driverEmail;
	}

	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(String busRoute) {
		this.busRoute = busRoute;
	}

	public List<String> getStudentsEmails() {
		return studentsEmails;
	}

	public void setStudentsEmails(List<String> studentsEmails) {
		this.studentsEmails = studentsEmails;
	}

    
    
}
