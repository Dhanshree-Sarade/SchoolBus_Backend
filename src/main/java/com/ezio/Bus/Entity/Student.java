package com.ezio.Bus.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentFirstName;
    private String studentLastName;
    private String studentGrade;
    private Integer studentAge;
    private Long parentId;
    private String pickupStop;
    private String dropStop;
    
    
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Student(Long id, String studentFirstName, String studentLastName, String studentGrade, Integer studentAge,
			Long parentId, String pickupStop, String dropStop) {
		super();
		this.id = id;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentGrade = studentGrade;
		this.studentAge = studentAge;
		this.parentId = parentId;
		this.pickupStop = pickupStop;
		this.dropStop = dropStop;
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


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
    
    
    

}
