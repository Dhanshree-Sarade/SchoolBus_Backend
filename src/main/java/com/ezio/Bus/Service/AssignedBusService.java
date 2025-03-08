package com.ezio.Bus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.AssignedBus;
import com.ezio.Bus.Entity.BusRoute;
import com.ezio.Bus.Repository.AssignedBusRepository;

@Service
public class AssignedBusService {
	
	@Autowired
	private AssignedBusRepository assignedBusRepository;
	
	public AssignedBus assignBusToDriver(AssignedBus assignedBus) {
		return assignedBusRepository.save(assignedBus);
	}
	
	public AssignedBus getBusDetailsByDriverId(Long driverId) {
        Optional<AssignedBus> assignedBus = assignedBusRepository.findByDriverId(driverId);
        return assignedBus.orElseThrow(() -> new RuntimeException("No bus assigned to this driver"));
    }
	
	public List<BusRoute> getBusRouteByDriverId(Long driverId) {
        return assignedBusRepository.findBusRouteByDriverId(driverId);
    }
}
