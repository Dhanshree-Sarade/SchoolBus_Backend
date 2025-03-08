package com.ezio.Bus.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.BusStop;
import com.ezio.Bus.Repository.BusStopRepository;

@Service
public class BusStopService {

	@Autowired
    private BusStopRepository busStopRepository;

    // ✅ Add Bus Stop
    public BusStop addBusStop(BusStop busStop) {
        return busStopRepository.save(busStop);
    }

    // ✅ Get all Bus Stops
    public List<BusStop> getAllBusStops() {
        return busStopRepository.findAll();
    }

    // ✅ Get Bus Stop by ID
    public Optional<BusStop> getBusStopById(Long id) {
        return busStopRepository.findById(id);
    }

    // ✅ Delete Bus Stop
    public void deleteBusStop(Long id) {
        busStopRepository.deleteById(id);
    }
    
    public BusStop getBusStopDetails(String stopName) {
        return busStopRepository.findByStopName(stopName)
                .orElseThrow(() -> new RuntimeException("Bus stop not found!"));
    }
    
    
    public List<Map<String, Object>> getBusStopsByAssignedBusId(Long assignedBusId) {
        List<Object[]> results = busStopRepository.findBusStopsByAssignedBusId(assignedBusId);
        System.err.println(results);
        return results.stream().map(obj -> {
            Map<String, Object> data = new HashMap<>();
            data.put("stopId", obj[0]);
            data.put("stopName", obj[1]);
            data.put("routeName", obj[2]);
            data.put("driverId", obj[3]);
            return data;
        }).toList();
    }
    
    public BusStop updateBusStop(Long id, BusStop updatedStop) {
        return busStopRepository.findById(id).map(stop -> {
            stop.setStopName(updatedStop.getStopName());
            return busStopRepository.save(stop);
        }).orElseThrow(() -> new RuntimeException("Bus stop not found"));
    }
}
