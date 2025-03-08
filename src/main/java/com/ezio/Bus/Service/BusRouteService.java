package com.ezio.Bus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.BusRoute;
import com.ezio.Bus.Entity.BusStop;
import com.ezio.Bus.Repository.BusRouteRepository;
import com.ezio.Bus.Repository.BusStopRepository;

import jakarta.transaction.Transactional;

@Service
public class BusRouteService {

	@Autowired
    private BusRouteRepository busRouteRepository;

    @Autowired
    private BusStopRepository busStopRepository;

    @Transactional
    public BusRoute addBusRoute(BusRoute busRoute) {
        for (BusStop stop : busRoute.getStops()) {
            stop.setBusRoute(busRoute);
        }
        return busRouteRepository.save(busRoute);
    }

    public List<BusRoute> getAllRoutes() {
        return busRouteRepository.findAll();
    }
    
    public boolean deleteBusRoute(Long id) {
        Optional<BusRoute> route = busRouteRepository.findById(id);
        if (route.isPresent()) {
            busRouteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public BusRoute updateBusRoute(Long id, BusRoute updatedRoute) {
        return busRouteRepository.findById(id).map(route -> {
            route.setRouteName(updatedRoute.getRouteName());
            route.setBusNumber(updatedRoute.getBusNumber());
            route.setBusNumberPlate(updatedRoute.getBusNumberPlate());
            return busRouteRepository.save(route);
        }).orElseThrow(() -> new RuntimeException("Bus route not found"));
    }
}
