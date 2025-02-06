package com.ezio.Bus.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class GoogleMapService {
	
	private static final String API_URL = "https://app.gomaps.pro/geocode/json";
    private static final String API_KEY = "AlzaSyarc0_lcxn5szW_2CdSITSvpFphBWmuACy"; // Replace with actual key
	
	@Value("${gomaps.api.key}")
    private String goMapsApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    // Convert address to latitude & longitude (Geocoding)
    public String getCoordinates(String address) {
        String url = "https://app.gomaps.pro/api/geocode/json?address=" + address + "&key=" + goMapsApiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    // Convert latitude & longitude to address (Reverse Geocoding)
    public String getAddress(double lat, double lng) {
        String url = "https://app.gomaps.pro/api/geocode/json?latlng=" + lat + "," + lng + "&key=" + goMapsApiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    
    public String getGeocode(String address) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Build API URL
        String url = API_URL + "?address=" + address + "&key=" + API_KEY;
        
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return "Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        }
    }

}
