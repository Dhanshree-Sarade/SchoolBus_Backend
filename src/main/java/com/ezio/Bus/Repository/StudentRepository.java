package com.ezio.Bus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezio.Bus.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// Find students by pickup stop
	List<Student> findByPickupStop(String pickupStop);

	Student findByEmailAndPassword(String email, String password);

	@Query("SELECT s FROM Student s WHERE s.pickupStop IN "
			+ "(SELECT bs.stopName FROM BusStop bs WHERE bs.busRoute.routeName = :routeName)")
	List<Student> findByBusRoute(@Param("routeName") String routeName);

//	// students get by driver id
//	@Query(value = """
//			SELECT s.* FROM student s
//			WHERE s.pickup_stop IN (
//			    SELECT bs.stop_name
//			    FROM bus_stop bs
//			    JOIN bus_route br ON bs.bus_route_id = br.id
//			    JOIN assigned_bus ab ON br.bus_number = ab.bus_number
//			    WHERE ab.driver_id = :driverId
//			)
//			""", nativeQuery = true)
//	List<Student> findStudentsByDriverId(@Param("driverId") Long driverId);
	
	@Query(value = """
		    SELECT s.* 
		    FROM student s
		    JOIN bus_stop bs ON s.pickup_stop = bs.stop_name
		    WHERE bs.bus_route_id = (
		        SELECT br.id  
		        FROM bus_route br  
		        JOIN assigned_bus ab ON br.bus_number_plate = ab.bus_number_plate  
		        WHERE ab.driver_id = :driverId
		    )
		    ORDER BY bs.id
		    """, nativeQuery = true)
		List<Student> findStudentsByDriverId(@Param("driverId") Long driverId);


	// same stop students mail send
//	@Query(value = "SELECT s.id, s.email, s.student_first_name, s.student_last_name, s.pickup_stop " + "FROM student s "
//			+ "WHERE s.pickup_stop = (" + "    SELECT MIN(bs.stop_name) " + // Fetch next stop
//			"    FROM bus_stop bs " + "    JOIN bus_route br ON bs.bus_route_id = br.id "
//			+ "    JOIN assigned_bus ab ON br.bus_number = ab.bus_number " + "    WHERE ab.driver_id = :driverId "
//			+ "    AND bs.stop_name > :currentStop" + // Get only the next stop
//			")", nativeQuery = true)
//	List<Object[]> findStudentsAtNextStop(@Param("driverId") Long driverId, @Param("currentStop") String currentStop);


	@Query(value = "SELECT s.pickup_stop, " +
            "GROUP_CONCAT(s.id) AS student_ids, " +
            "GROUP_CONCAT(s.student_first_name) AS student_first_names, " +
            "GROUP_CONCAT(s.student_last_name) AS student_last_names, " +
            "GROUP_CONCAT(s.email) AS student_emails, " +
            "COUNT(s.id) AS student_count " +
            "FROM student s " +
            "WHERE s.pickup_stop IN (" +
            "    SELECT bs.stop_name " +
            "    FROM bus_stop bs " +
            "    JOIN bus_route br ON bs.bus_route_id = br.id " +
            "    JOIN assigned_bus ab ON br.bus_number = ab.bus_number " +
            "    WHERE ab.driver_id = :driverId " +
            ") " +
            "GROUP BY s.pickup_stop",
    nativeQuery = true)
	List<Object[]> findStudentsAtNextStop(@Param("driverId") Long driverId);



	@Query(value = """
		    SELECT s.* 
		    FROM student s
		    JOIN bus_stop bs ON s.pickup_stop = bs.stop_name
		    WHERE bs.bus_route_id = (
		        SELECT br.id  
		        FROM bus_route br  
		        JOIN assigned_bus ab ON br.bus_number_plate = ab.bus_number_plate  
		        WHERE ab.driver_id = :driverId
		    )
		    ORDER BY bs.id DESC
		    """, nativeQuery = true)
		List<Student> findStudentsByDriverIdReverse(@Param("driverId") Long driverId);


	// Returns all students with the given email
    List<Student> findByEmail(String email);

}
