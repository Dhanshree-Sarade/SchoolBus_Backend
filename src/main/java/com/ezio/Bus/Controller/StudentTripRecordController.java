package com.ezio.Bus.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezio.Bus.Entity.AssignedBus;
import com.ezio.Bus.Entity.BusStop;
import com.ezio.Bus.Entity.Driver;
import com.ezio.Bus.Entity.Student;
import com.ezio.Bus.Entity.StudentTripRecord;
import com.ezio.Bus.Repository.AssignedBusRepository;
import com.ezio.Bus.Repository.BusStopRepository;
import com.ezio.Bus.Repository.DriverRepository;
import com.ezio.Bus.Repository.StudentRepository;
import com.ezio.Bus.Repository.StudentTripRecordRepository;
import com.ezio.Bus.Service.StudentTripRecordService;

@RestController
@RequestMapping("/trips")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentTripRecordController {
	
	@Autowired
	private StudentTripRecordService studentTripRecordService;
	
	@Autowired
	private StudentTripRecordRepository studentTripRecordRepository;
	
	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AssignedBusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BusStopRepository busStopRepository;
	
	@GetMapping
    public List<StudentTripRecord> getAllTrips() {
        return studentTripRecordService.getAllTrips();
    }

    @GetMapping("/date/{date}")
    public List<StudentTripRecord> getTripsByDate(@PathVariable LocalDate date) {
        return studentTripRecordService.getTripsByDate(date);
    }
    
//    @PostMapping("/record-trip")
//    public StudentTripRecord recordStudentTrip(@RequestBody StudentTripRecord tripRecord) {
//        return studentTripRecordService.saveTripRecord(tripRecord);
//    }
    
    @PostMapping("/record-trip")
    public ResponseEntity<String> recordTrip(@RequestBody StudentTripRecord tripRecord) {
        try {
            // Fetch existing entities to ensure they exist
            Student student = studentRepository.findById(tripRecord.getStudent().getId()).orElse(null);
            AssignedBus bus = busRepository.findById(tripRecord.getBus().getId()).orElse(null);
            Driver driver = driverRepository.findById(tripRecord.getDriver().getDid()).orElse(null);
            BusStop pickupStop = busStopRepository.findById(tripRecord.getPickupStop().getId()).orElse(null);
            BusStop dropStop = busStopRepository.findById(tripRecord.getDropStop().getId()).orElse(null);

            if (student == null || bus == null || driver == null || pickupStop == null || dropStop == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid references in trip record");
            }

            // Save the trip record in the database
            tripRecord.setStudent(student);
            tripRecord.setBus(bus);
            tripRecord.setDriver(driver);
            tripRecord.setPickupStop(pickupStop);
            tripRecord.setDropStop(dropStop);
            studentTripRecordRepository.save(tripRecord);

            return ResponseEntity.ok("Trip record saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error storing trip record");
        }
    }


}
