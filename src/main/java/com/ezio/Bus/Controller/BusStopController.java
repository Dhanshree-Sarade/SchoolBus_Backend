package com.ezio.Bus.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ezio.Bus.Entity.BusStop;
import com.ezio.Bus.Service.BusStopService;

@RestController
@RequestMapping("/api/bus-stops")
@CrossOrigin(origins = "http://localhost:5173")  // Allow frontend access
public class BusStopController {

    @Autowired
    private BusStopService busStopService;

    @PostMapping("/add")
    public BusStop addBusStop(@RequestBody BusStop busStop) {
        return busStopService.addBusStop(busStop);
    }

    @GetMapping("/all")
    public List<BusStop> getAllBusStops() {
        return busStopService.getAllBusStops();
    }

    @GetMapping("/{id}")
    public Optional<BusStop> getBusStopById(@PathVariable Long id) {
        return busStopService.getBusStopById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBusStop(@PathVariable Long id) {
        busStopService.deleteBusStop(id);
        return "Bus stop deleted successfully";
    }
    
    @PutMapping("/stops/update/{id}")
    public BusStop updateBusStop(@PathVariable Long id, @RequestBody BusStop busStop) {
        return busStopService.updateBusStop(id, busStop);
    }
    
    @GetMapping("/details")
    public ResponseEntity<?> getBusStopDetails(@RequestParam String stopName) {
        try {
            BusStop busStop = busStopService.getBusStopDetails(stopName);
            Map<String, Object> response = new HashMap<>();
            response.put("stopName", busStop.getStopName());

            if (busStop.getBusRoute() != null) {
                Map<String, Object> routeDetails = new HashMap<>();
                routeDetails.put("routeName", busStop.getBusRoute().getRouteName());
                routeDetails.put("busNumber", busStop.getBusRoute().getBusNumber());
                routeDetails.put("busNumberPlate", busStop.getBusRoute().getBusNumberPlate());
                response.put("busRoute", routeDetails);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus stop not found!");
        }
    }
    
    @GetMapping("/get/{assignedBusId}")
    @ResponseBody
    public List<Map<String, Object>> getBusStops(@PathVariable Long assignedBusId) {
        return busStopService.getBusStopsByAssignedBusId(assignedBusId);
    }
}
