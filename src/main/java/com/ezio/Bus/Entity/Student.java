package com.ezio.Bus.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentFirstName;
    private String studentLastName;
    private String studentGrade;
    private Integer studentAge;
    private String pickupStop;
    private String dropStop;
    
    private String parentFirstName;
    private String parentLastName;
    private String mobNo;
    private String address;
    private String email;
    private String password;
    
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentTripRecord> tripRecords;
    
    
	public Student(Long id, String studentFirstName, String studentLastName, String studentGrade, Integer studentAge,
			String pickupStop, String dropStop, String parentFirstName, String parentLastName, String mobNo,
			String address, String email, String password, List<StudentTripRecord> tripRecords) {
		super();
		this.id = id;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentGrade = studentGrade;
		this.studentAge = studentAge;
		this.pickupStop = pickupStop;
		this.dropStop = dropStop;
		this.parentFirstName = parentFirstName;
		this.parentLastName = parentLastName;
		this.mobNo = mobNo;
		this.address = address;
		this.email = email;
		this.password = password;
		this.tripRecords = tripRecords;
	}


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public List<StudentTripRecord> getTripRecords() {
		return tripRecords;
	}


	public void setTripRecords(List<StudentTripRecord> tripRecords) {
		this.tripRecords = tripRecords;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStudentFirstName() {
		return studentFirstName;
	}


	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}


	public String getStudentLastName() {
		return studentLastName;
	}


	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}


	public String getStudentGrade() {
		return studentGrade;
	}


	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}


	public Integer getStudentAge() {
		return studentAge;
	}


	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}



	public String getPickupStop() {
		return pickupStop;
	}


	public void setPickupStop(String pickupStop) {
		this.pickupStop = pickupStop;
	}


	public String getDropStop() {
		return dropStop;
	}


	public void setDropStop(String dropStop) {
		this.dropStop = dropStop;
	}


	public String getParentFirstName() {
		return parentFirstName;
	}


	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}


	public String getParentLastName() {
		return parentLastName;
	}


	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}

	public String getMobNo() {
		return mobNo;
	}


	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
    
    
	
}
