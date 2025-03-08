package com.ezio.Bus.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ezio.Bus.Entity.BusRoute;
import com.ezio.Bus.Entity.BusStop;
import com.ezio.Bus.Service.BusRouteService;

@RestController
@RequestMapping("/api/bus-routes")
@CrossOrigin(origins = "http://localhost:5173")  // Allow frontend access
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @PostMapping("/add")
    public ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) {
        BusRoute savedRoute = busRouteService.addBusRoute(busRoute);
        return ResponseEntity.ok(savedRoute);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusRoute>> getAllRoutes() {
        return ResponseEntity.ok(busRouteService.getAllRoutes());
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBusRoute(@PathVariable Long id) {
        boolean deleted = busRouteService.deleteBusRoute(id);
        if (deleted) {
            return ResponseEntity.ok("Bus route deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public BusRoute updateBusRoute(@PathVariable Long id, @RequestBody BusRoute busRoute) {
        return busRouteService.updateBusRoute(id, busRoute);
    }
}
