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

import com.ezio.Bus.Service.AdminService;

@Controller
@CrossOrigin(origins = "http://localhost:5173") 
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
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
	
	@PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
        String userType = adminService.validateUserCredentials(email, password);
        Map<String, String> response = new HashMap<>();

        if (!userType.equals("INVALID")) {
            response.put("message", userType + " Login Successful");
            response.put("userRole", userType); // 🔹 Send userRole to frontend
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
