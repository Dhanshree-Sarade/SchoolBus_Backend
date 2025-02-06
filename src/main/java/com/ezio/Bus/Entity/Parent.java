package com.ezio.Bus.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	private String parentFirstName;
    private String parentLastName;
    private String candidateName;
    private String mobNo;
    private String address;
    private String email;
    private String password;
    
    
	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Parent(Long pid, String parentFirstName, String parentLastName, String candidateName, String mobNo,
			String address, String email, String password) {
		super();
		this.pid = pid;
		this.parentFirstName = parentFirstName;
		this.parentLastName = parentLastName;
		this.candidateName = candidateName;
		this.mobNo = mobNo;
		this.address = address;
		this.email = email;
		this.password = password;
	}


	public Long getPid() {
		return pid;
	}


	public void setPid(Long pid) {
		this.pid = pid;
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


	public String getCandidateName() {
		return candidateName;
	}


	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
