package com.ezio.Bus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezio.Bus.Entity.BusStop;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {

	List<BusStop> findByBusRouteRouteName(String routeName);
	
	Optional<BusStop> findByStopName(String stopName);
	
	@Query("SELECT bs.id, bs.stopName, br.routeName, ab.driverId " +
	           "FROM BusStop bs " +
	           "JOIN bs.busRoute br " +
	           "JOIN AssignedBus ab ON ab.busId = br.id " +
	           "WHERE ab.id = :assignedBusId")
	    List<Object[]> findBusStopsByAssignedBusId(@Param("assignedBusId") Long assignedBusId);
	    
	
}