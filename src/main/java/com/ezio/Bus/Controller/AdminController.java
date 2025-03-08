package com.ezio.Bus.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezio.Bus.Entity.Driver;
import com.ezio.Bus.Repository.DriverRepository;
import com.ezio.Bus.Service.AdminService;

@Controller
@CrossOrigin(origins = "http://localhost:5173") 
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private DriverRepository driverRepository;
	
	
	//only admin login method
//	 @PostMapping("/admin/login")
//	    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//	        boolean isValid = adminService.validateAdminCredentials(email, password);
//
//	        if (isValid) {
//	            return ResponseEntity.ok("Login successful");
//	        } else {
//	            return ResponseEntity.status(401).body("Invalid credentials");
//	        }
//	    }

	
	
	//admin, parent and driver login method 
//	 @PostMapping("/login")
//	    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//	        String userType = adminService.validateUserCredentials(email, password);
//
//	        switch (userType) {
//	            case "ADMIN":
//	                return ResponseEntity.ok("Admin Login Successful");
//	            case "PARENT":
//	                return ResponseEntity.ok("Parent Login Successful");
//	            case "DRIVER":
//	                return ResponseEntity.ok("Driver Login Successful");
//	            default:
//	                return ResponseEntity.status(401).body("Invalid credentials");
//	        }
//	    }
	
//	@PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
//        String userType = adminService.validateUserCredentials(email, password);
//        Map<String, String> response = new HashMap<>();
//
//        if (!userType.equals("INVALID")) {
//            response.put("message", userType + " Login Successful");
//            response.put("userRole", userType); // 🔹 Send userRole to frontend
//            return ResponseEntity.ok(response);
//        } else {
//            response.put("message", "Invalid credentials");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }
	
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
//	    String userType = adminService.validateUserCredentials(email, password);
//	    Map<String, String> response = new HashMap<>();
//
//	    if (!userType.equals("INVALID")) {
//	        response.put("message", userType + " Login Successful");
//	        response.put("userRole", userType); // 🔹 Send userRole to frontend
//	        
//	        if (userType.equals("DRIVER")) {
//	            // Fetch and send the driver's ID if the user is a driver
//	            Driver driver = driverRepository.findByDriverEmailAndDriverPassword(email, password);
//	            if (driver != null) {
//	                response.put("driverId", driver.getDid().toString()); // Add driver ID to the response
//	            }
//	        }
//
//	        return ResponseEntity.ok(response);
//	    } else {
//	        response.put("message", "Invalid credentials");
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//	    }
//	}
	
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
//	    Map<String, String> userData = adminService.validateUserCredentials(email, password);
//	    Map<String, String> response = new HashMap<>();
//
//	    String userRole = userData.get("userRole");
//
//	    if (!userRole.equals("INVALID")) {
//	        response.put("message", userRole + " Login Successful");
//	        response.put("userRole", userRole);
//	        response.put("email", email);
//	        response.put("password", password); // Optional: Store only if needed
//	        
//	        // If the user is a Parent or Driver, store their ID
//	        if (userData.containsKey("userId")) {
//	            response.put("userId", userData.get("userId"));
//	        }
//
//	        return ResponseEntity.ok(response);
//	    } else {
//	        response.put("message", "Invalid credentials");
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//	    }
//	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
	    Map<String, String> userData = adminService.validateUserCredentials(email, password);
	    Map<String, String> response = new HashMap<>();

	    System.out.println("📌 Retrieved User Data from DB: " + userData); // Debugging print

	    if (userData == null || userData.isEmpty()) {
	        response.put("message", "Invalid credentials");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }

	    String userRole = userData.get("userRole");

	    if (!"INVALID".equals(userRole)) {
	        response.put("message", userRole + " Login Successful");
	        response.put("userRole", userRole);
	        response.put("email", email);
	        response.put("userId", userData.get("userId"));

	        if ("DRIVER".equals(userRole)) {
	            response.put("firstName", userData.get("driverFirstName"));
	            response.put("lastName", userData.get("driverLastName"));
	        } else if ("PARENT".equals(userRole)) {
	            response.put("firstName", userData.get("parentFirstName"));
	            response.put("lastName", userData.get("parentLastName"));
	        }

	        System.err.println("✅ Final Response Data: " + response); // Debugging print

	        return ResponseEntity.ok(response);
	    } else {
	        response.put("message", "Invalid credentials");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}


}
