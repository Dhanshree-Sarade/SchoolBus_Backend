package com.ezio.Bus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezio.Bus.Service.GoogleMapService;

//import com.ezio.Bus.Service.GoogleMapService;

@Controller
public class GoogleMapController {

	@Autowired
	private GoogleMapService googleMapService;
	
	// API to get latitude & longitude from address
    @GetMapping("/geocode")
    public ResponseEntity<String> getGeocode(@RequestParam String address) {
        String response = googleMapService.getGeocode(address);
        return ResponseEntity.ok(response);
    }

    // API to get address from latitude & longitude
    @GetMapping("/reverse-geocode")
    public ResponseEntity<String> getAddress(@RequestParam double lat, @RequestParam double lng) {
        String response = googleMapService.getAddress(lat, lng);
        return ResponseEntity.ok(response);
    }
}
