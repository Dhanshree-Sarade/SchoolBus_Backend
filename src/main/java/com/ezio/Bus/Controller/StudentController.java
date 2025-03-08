package com.ezio.Bus.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezio.Bus.Entity.BusRoute;
import com.ezio.Bus.Entity.Student;
import com.ezio.Bus.Repository.StudentRepository;
import com.ezio.Bus.Service.EmailService;
import com.ezio.Bus.Service.StudentService;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

	@Autowired
    private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmailService emailService;

    // Add new student
//    @PostMapping("/addStudent")
//    @ResponseBody
//    public Student addStudent(@RequestBody Student student) {
//        return studentService.addDetails(student);
//    }

	@PostMapping("/addStudent")
	@ResponseBody
	public Student addStudent(@RequestBody Student student) {
	    Student savedStudent = studentService.addDetails(student);

	    // Sending email after student is successfully added
	    String parentEmail = savedStudent.getEmail();
	    String subject = "Student Registration Confirmation";
	    String body = "Dear " + savedStudent.getParentFirstName() + " " + savedStudent.getParentLastName() + ",<br><br>"
	                + "Your child, " + savedStudent.getStudentFirstName() + " " + savedStudent.getStudentLastName() 
	                + ", has been successfully registered for school transport.<br><br>"
	                + "Pickup Stop: " + savedStudent.getPickupStop() + "<br>"
	                + "Drop Stop: " + savedStudent.getDropStop() + "<br><br>"
	                + "Thank you for choosing our service!<br><br>"
	                + "Regards,<br>School Transport Team";

	    // Calling email service to send email
	    emailService.sendSimpleEmail(parentEmail, subject, body);

	    return savedStudent;
	}

    // Get all students
    @GetMapping("/getStudents")
    @ResponseBody
    public List<Student> showStudents() {
        return studentService.getDetails();
    }

    // Get student by ID
    @GetMapping("/getStudent/{id}")
    @ResponseBody
    public Student showStudentById(@PathVariable Long id) {
        return studentService.getDetailsById(id);
    }

    // Update student details
    @PutMapping("/editStudent/{id}")
    @ResponseBody
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return studentService.editDetails(student, id);
    }

    // Delete student by ID
    @DeleteMapping("/deleteStudent/{id}")
    @ResponseBody
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteDetails(id);
    }
	
//    @PostMapping("/sendPickupMail/{studentId}")
//    public ResponseEntity<String> sendPickupMail(@PathVariable Long studentId) {
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//
//        if (studentOptional.isPresent()) {
//            Student student = studentOptional.get();
//            String parentEmail = student.getEmail();
//            System.out.println("Sending email to: " + parentEmail);
//            String studentName = student.getStudentFirstName() + " " + student.getStudentLastName();
//
//            String subject = "School Bus Pickup Notification";
//            String message = "Dear Parent,\n\nYour child " + studentName + " has been picked up successfully.\n\nRegards,\nSchool Transport Team";
//
//            emailService.sendMail(parentEmail, subject, message); // Calls the correct method
//            return ResponseEntity.ok("Pickup email sent successfully to " + parentEmail);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
//        }
//    }

    @PostMapping("/sendPickupMail/{studentId}")
    public ResponseEntity<String> sendPickupMail(@PathVariable Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            String parentEmail = student.getEmail();
            String studentName = student.getStudentFirstName() + " " + student.getStudentLastName();

            String subject = "School Bus Pickup Notification";
            String message = "Dear Parent,\n\nYour child " + studentName + " has been picked up successfully.\n\nRegards,\nSchool Transport Team";

            emailService.sendMail(parentEmail, subject, message);

            return ResponseEntity.ok("Pickup email sent successfully to " + parentEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        }
    }

    @PostMapping("/notifyNextStudent/{studentId}")
    public ResponseEntity<String> notifyNextStudent(@PathVariable Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            String parentEmail = student.getEmail();
            String studentName = student.getStudentFirstName() + " " + student.getStudentLastName();
            String pickupStop = student.getPickupStop();

            String subject = "School Bus Arrival Alert";
            String message = "Dear Parent,\n\nThe school bus has arrived at the previous stop. " +
                             "Please ensure your child, " + studentName + ", is ready at the stop: " + pickupStop + ".\n\n" +
                             "Regards,\nSchool Transport Team";

            emailService.sendMail(parentEmail, subject, message);

            return ResponseEntity.ok("Next stop notification sent successfully to " + parentEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Next student not found!");
        }
    }

    
    @PostMapping("/sendDropMail/{studentId}")
    public ResponseEntity<String> sendDropMail(@PathVariable Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            String parentEmail = student.getEmail();
            String studentName = student.getStudentFirstName() + " " + student.getStudentLastName();

            String subject = "School Bus Drop Notification";
            String message = "Dear Parent,\n\nYour child " + studentName + " has been dropped off successfully.\n\nRegards,\nSchool Transport Team";

            emailService.sendMail(parentEmail, subject, message); // Calls the overloaded sendMail method
            return ResponseEntity.ok("Drop email sent successfully to " + parentEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        }
    }
    
    @PostMapping("/sendAbsentMail/{studentId}")
    public ResponseEntity<String> sendAbsentMail(@PathVariable Long studentId){
    	Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            String parentEmail = student.getEmail();
            String studentName = student.getStudentFirstName() + " " + student.getStudentLastName();

            String subject = "Student is Absent Today notification.";
            String message = "Dear Parent,\n\nYour child " + studentName + " was absent today for the school bus pickup.\n\nRegards,\nSchool Transport Team";

            emailService.sendMail(parentEmail, subject, message); // Calls the overloaded sendMail method
            return ResponseEntity.ok("Absent email sent successfully to " + parentEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        }
    }
    
//    @PostMapping("/reachedStop")
//    public String notifyNextStop(@RequestParam String routeName, @RequestParam String currentStop) {
//        return studentService.notifyNextStop(routeName, currentStop);
//    }
    
//    @PostMapping("/notifyNextStop/{driverId}")
//    public ResponseEntity<String> notifyNextStop(@PathVariable Long driverId, @RequestParam String currentStop) {
//        studentService.notifyNextStopStudents(driverId, currentStop);
//        return ResponseEntity.ok("Notification sent to next stop students!");
//    }
    
    @PostMapping("/notifyNextStop/{driverId}")
    public ResponseEntity<String> notifyNextStop(@PathVariable Long driverId) {
        studentService.notifyNextStopStudents(driverId);
        return ResponseEntity.ok("Notification sent to next stop students!");
    }

    
//    @GetMapping("/students-with-bus")
//    public ResponseEntity<List<Map<String, Object>>> getStudentsWithBus() {
//        List<Student> students = studentService.getAllStudentsWithBus(); // Fetch students with assigned buses
//        List<Map<String, Object>> response = new ArrayList<>();
//
//        for (Student student : students) {
//            Map<String, Object> studentData = new HashMap<>();
//            studentData.put("studentId", student.getId());
//            studentData.put("studentName", student.getStudentFirstName() + " " + student.getStudentLastName());
//            studentData.put("studentGrade", student.getStudentGrade());
//
//            if (student.getPickupStop() != null && student.getPickupStop().getBusRoute() != null) {
//                BusRoute route = student.getPickupStop().getBusRoute();
//                Map<String, Object> busDetails = new HashMap<>();
//                busDetails.put("routeName", route.getRouteName());
//                busDetails.put("busNumber", route.getBusNumber());
//                busDetails.put("busNumberPlate", route.getBusNumberPlate());
//                studentData.put("bus", busDetails);
//            } else {
//                studentData.put("bus", null);
//            }
//
//            response.add(studentData);
//        }
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/by-route")
    public ResponseEntity<List<Student>> getStudentsByRoute(@RequestParam String routeName) {
        List<Student> students = studentService.getStudentsByRoute(routeName);
        return ResponseEntity.ok(students);
    }

    
    @GetMapping("/assigned/{driverId}")
    @ResponseBody
    public List<Student> getAssignedStudents(@PathVariable Long driverId) {
        return studentService.getStudentsByDriver(driverId);
    }
    
//    @GetMapping("/assignedReverse/{driverId}")
//    @ResponseBody
//    public List<Student> getAssignedStudentsReverse(@PathVariable Long driverId) {
//    	List<Student> students =  studentRepository.findAll();
//    	Collections.reverse(students);
//        return students;
//    }
    
    
    //reverse stops
    @GetMapping("/assignedReverse/{driverId}")
    @ResponseBody
    public List<Student> getAssignedStudentsReverse(@PathVariable Long driverId) {
        return studentService.getStudentsByDriverReverse(driverId);
    }


    @GetMapping("/find-student-by-email")
    public ResponseEntity<?> getStudentsByEmail(@RequestParam String email) {
        List<Student> students = studentService.findStudentsByEmail(email);
        if (students == null || students.isEmpty()) {
            return new ResponseEntity<>("No students found with that email", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
}
