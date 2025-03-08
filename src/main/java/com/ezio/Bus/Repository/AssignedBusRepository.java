package com.ezio.Bus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezio.Bus.Entity.AssignedBus;
import com.ezio.Bus.Entity.BusRoute;

@Repository
public interface AssignedBusRepository extends JpaRepository<AssignedBus, Long>{
	
	Optional<AssignedBus> findByDriverId(Long driverId);
	
	@Query("SELECT br FROM BusRoute br " +
	           "JOIN AssignedBus ab ON br.busNumber = ab.busNumber " +
	           "WHERE ab.driverId = :driverId")
	    List<BusRoute> findBusRouteByDriverId(@Param("driverId") Long driverId);

}
