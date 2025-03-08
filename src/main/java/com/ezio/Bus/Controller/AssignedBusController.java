package com.ezio.Bus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezio.Bus.Entity.AssignedBus;
import com.ezio.Bus.Entity.BusRoute;
import com.ezio.Bus.Entity.ReportedIssue;
import com.ezio.Bus.Repository.ReportedIssueRepository;
import com.ezio.Bus.Service.AssignedBusService;
import com.ezio.Bus.Service.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/assigned-bus")
public class AssignedBusController {

	@Autowired
    private AssignedBusService assignedBusService;
	
	 @Autowired
	    private EmailService emailService;
	 
	 @Autowired
	 private ReportedIssueRepository reportedIssueRepository; 


    @PostMapping("/assign")
    public ResponseEntity<String> assignBusToDriver(@RequestBody AssignedBus assignedBus) {
        assignedBusService.assignBusToDriver(assignedBus);
        return ResponseEntity.ok("Bus assigned successfully!");
    }
    
    @GetMapping("/getBusByDriver/{driverId}")
    public ResponseEntity<AssignedBus> getBusByDriver(@PathVariable Long driverId) {
        AssignedBus assignedBus = assignedBusService.getBusDetailsByDriverId(driverId);
        return ResponseEntity.ok(assignedBus);
    }
    
//    @GetMapping("/driver/{driverId}")
//    public ResponseEntity<List<BusRoute>> getBusRoutes(@PathVariable Long driverId) {
//        List<BusRoute> busRoutes = assignedBusService.getBusRouteByDriverId(driverId);
//        if (busRoutes.isEmpty()) {
//            return ResponseEntity.noContent().build(); // 204 No Content if no routes assigned
//        }
//        return ResponseEntity.ok(busRoutes);
//    }
    
    
    @GetMapping("/busRouteByDriverId/{driverId}")
    public ResponseEntity<List<BusRoute>> getBusRoutesByDriver(@PathVariable Long driverId) {
        List<BusRoute> busRoutes = assignedBusService.getBusRouteByDriverId(driverId);
        if (busRoutes.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no routes assigned
        }
        return ResponseEntity.ok(busRoutes);
    }
    
    
    @PostMapping("/reportIssue")
    public ResponseEntity<String> reportIssue(@RequestBody ReportedIssue request) {
        try {
            // Save the issue in the database
            ReportedIssue savedIssue = reportedIssueRepository.save(request);
            
            // Send an email notification
            String subject = "Urgent: Issue Reported on Bus Route " + request.getBusRoute();
            String body = "Issue Type: " + request.getIssueType() + "\n"
                    + "Description: " + request.getDescription() + "\n"
                    + "Reported By: " + request.getDriverEmail();
            emailService.sendEmail(request.getStudentsEmails(), subject, body);
            
            return ResponseEntity.ok("Issue reported successfully and saved to database!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to report issue: " + e.getMessage());
        }
    }

}
