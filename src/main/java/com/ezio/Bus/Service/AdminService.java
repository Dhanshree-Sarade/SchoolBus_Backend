package com.ezio.Bus.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.Driver;
import com.ezio.Bus.Entity.Parent;
import com.ezio.Bus.Repository.DriverRepository;
import com.ezio.Bus.Repository.ParentRepository;

@Service
public class AdminService {

	 @Value("${admin.email}")
	    private String adminEmail;

	    @Value("${admin.password}")
	    private String adminPassword;

	  
//	    public boolean validateAdminCredentials(String email, String password) {
//	        return adminEmail.equals(email) && adminPassword.equals(password);
//	    }
	    
	    
	    private final ParentRepository parentRepository;
	    private final DriverRepository driverRepository;

	    public AdminService(ParentRepository parentRepository, DriverRepository driverRepository) {
	        this.parentRepository = parentRepository;
	        this.driverRepository = driverRepository;
	    }

	    public String validateUserCredentials(String email, String password) {
	        // Check Admin Credentials
	        if (email.equals(adminEmail) && password.equals(adminPassword)) {
	            return "ADMIN";
	        }

	        // Check Parent Credentials
	        Parent parent = parentRepository.findByEmailAndPassword(email, password);
	        if (parent != null) {
	            return "PARENT";
	        }

	        // Check Driver Credentials
	        Driver driver = driverRepository.findByDriverEmailAndDriverPassword(email, password);
	        if (driver != null) {
	            return "DRIVER";
	        }

	        return "INVALID";
	    }
}
