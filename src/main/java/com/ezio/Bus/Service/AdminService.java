package com.ezio.Bus.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.Driver;
import com.ezio.Bus.Entity.Student;
import com.ezio.Bus.Repository.DriverRepository;
import com.ezio.Bus.Repository.StudentRepository;

@Service
public class AdminService {

	 @Value("${admin.email}")
	    private String adminEmail;

	    @Value("${admin.password}")
	    private String adminPassword;
	    
	    @Autowired
	    private StudentRepository studentRepository;
	    
	    @Autowired
	    private DriverRepository driverRepository;

	  
//	    public boolean validateAdminCredentials(String email, String password) {
//	        return adminEmail.equals(email) && adminPassword.equals(password);
//	    }
	    
	    
//	    private final StudentRepository studentRepository;
//	    private final DriverRepository driverRepository;
//
//	    public AdminService(StudentRepository studentRepository, DriverRepository driverRepository) {
//	        this.studentRepository = studentRepository;
//	        this.driverRepository = driverRepository;
//	    }
//
//	    public String validateUserCredentials(String email, String password) {
//	        // Check Admin Credentials
//	        if (email.equals(adminEmail) && password.equals(adminPassword)) {
//	            return "ADMIN";
//	        }
//
//	        // Check Parent Credentials from Student Entity
//	        Student student = studentRepository.findByEmailAndPassword(email, password);
//	        if (student != null) {
//	            return "PARENT";
//	        }
//
//	        // Check Driver Credentials
//	        Driver driver = driverRepository.findByDriverEmailAndDriverPassword(email, password);
//	        if (driver != null) {
//	            return "DRIVER";
//	        }
//
//	        return "INVALID";
//	    }

	    
//	    public Map<String, String> validateUserCredentials(String email, String password) {
//	        Map<String, String> userData = new HashMap<>();
//
//	        // Check Admin Credentials
//	        if (email.equals(adminEmail) && password.equals(adminPassword)) {
//	            userData.put("userRole", "ADMIN");
//	            return userData;
//	        }
//
//	        // Check Parent Credentials from Student Entity
//	        Student student = studentRepository.findByEmailAndPassword(email, password);
//	        if (student != null) {
//	            userData.put("userRole", "PARENT");
//	            userData.put("userId", student.getId().toString()); // Store Parent ID
//	            return userData;
//	        }
//
//	        // Check Driver Credentials
//	        Driver driver = driverRepository.findByDriverEmailAndDriverPassword(email, password);
//	        if (driver != null) {
//	            userData.put("userRole", "DRIVER");
//	            userData.put("userId", driver.getDid().toString()); // Store Driver ID
//	            return userData;
//	        }
//
//	        userData.put("userRole", "INVALID");
//	        return userData;
//	    }
	    
	    public Map<String, String> validateUserCredentials(String email, String password) {
	        Map<String, String> userData = new HashMap<>();

	        // Check Admin Credentials
	        if (email.equals(adminEmail) && password.equals(adminPassword)) {
	            userData.put("userRole", "ADMIN");
	            return userData;
	        }

	        // Check Parent Credentials from Student Entity
	        Student student = studentRepository.findByEmailAndPassword(email, password);
	        if (student != null) {
	            userData.put("userRole", "PARENT");
	            userData.put("userId", student.getId().toString());
	            userData.put("parentFirstName", student.getParentFirstName());
	            userData.put("parentLastName", student.getParentLastName());
	            return userData;
	        }

	        // Check Driver Credentials
	        Driver driver = driverRepository.findByDriverEmailAndDriverPassword(email, password);
	        if (driver != null) {
	            userData.put("userRole", "DRIVER");
	            userData.put("userId", driver.getDid().toString());
	            userData.put("driverFirstName", driver.getDriverFirstName());
	            userData.put("driverLastName", driver.getDriverLastName());

	            // Print Driver's Full Name to Console
	            System.out.println("🚌 Driver Logged In: " + driver.getDriverFirstName() + " " + driver.getDriverLastName());
	            return userData;
	        }

	        userData.put("userRole", "INVALID");
	        return userData;
	    }

}
