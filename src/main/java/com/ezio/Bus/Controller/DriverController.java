package com.ezio.Bus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezio.Bus.Entity.Driver;
import com.ezio.Bus.Service.DriverService;
import com.ezio.Bus.Service.EmailService;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/addDriver")
    @ResponseBody
    public ResponseEntity<String> addDriver(@RequestBody Driver driver) {
        try {
            // Save driver to the database
            Driver savedDriver = driverService.saveDriver(driver);

            // Send email after successful registration
            String subject = "School Bus Driver Registration Successful";
            String body = "Dear " + savedDriver.getDriverFirstName() + ",\n\n" +
                    "Congratulations! You have successfully registered as a school bus driver.\n" +
                    "Please contact the school administration for further details.\n\n" +
                    "Best Regards,\n" +
                    "School Management Team";
            

            return ResponseEntity.ok("Driver added successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add driver.");
        }
    }

	@GetMapping("/getDriver")
	@ResponseBody
	public List<Driver> showDriver(){
		return driverService.showDriver();
	}
	
	@GetMapping("/getDriver/{did}")
	@ResponseBody
	public Driver showDriverById(@PathVariable Long did) {
		return driverService.showDriverById(did);
	}
	
	@PutMapping("/editDriver")
	@ResponseBody
	public Driver updateDriver(@RequestBody Driver driver) {
		return driverService.editDriver(driver);
	}
	
	@DeleteMapping("/deleteDriver/{did}")
	@ResponseBody
	public String removeData(@PathVariable Long did) {
		return driverService.deleteDriver(did);
	}
}
