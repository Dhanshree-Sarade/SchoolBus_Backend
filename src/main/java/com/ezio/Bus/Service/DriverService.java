package com.ezio.Bus.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.Driver;
import com.ezio.Bus.Repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	 public Driver saveDriver(Driver driver) {
	        return driverRepository.save(driver);
	    }
	
	public List<Driver> showDriver(){
		return driverRepository.findAll();
	}
	
	public Driver showDriverById(Long id) {
		return driverRepository.findById(id).orElse(null);
	}
	
	public Driver editDriver(Driver driver) {
	    Driver existingDriver = driverRepository.findById(driver.getDid()).orElse(null);
	    if (existingDriver != null) {
	        existingDriver.setDriverFirstName(driver.getDriverFirstName());
	        existingDriver.setDriverLastName(driver.getDriverLastName());
	        existingDriver.setDriverEmail(driver.getDriverEmail());
	        existingDriver.setDriverMob(driver.getDriverMob());
	        existingDriver.setDriverPassword(driver.getDriverPassword());
	        return driverRepository.save(existingDriver);  // Save and return the updated driver
	    }
	    return null;  // Return null only if the driver does not exist
	}

	
	public String deleteDriver(Long did) {
		driverRepository.deleteById(did);
		return "Driver Record Deleted Successfully.....";
	}
}
