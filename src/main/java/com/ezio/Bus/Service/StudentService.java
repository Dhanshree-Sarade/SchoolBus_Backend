package com.ezio.Bus.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.BusStop;
import com.ezio.Bus.Entity.Student;
import com.ezio.Bus.Repository.BusStopRepository;
import com.ezio.Bus.Repository.StudentRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class StudentService {

	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
	private BusStopRepository busStopRepository;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private EmailService emailService;

    // Add a new student
    public Student addDetails(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getDetails() {
        return studentRepository.findAll();
    }

    // Get a student by ID
    public Student getDetailsById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null); // Return null if student not found
    }

    // Update student details
    public Student editDetails(Student newStudent, Long id) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setStudentFirstName(newStudent.getStudentFirstName());
            existingStudent.setStudentLastName(newStudent.getStudentLastName());
            existingStudent.setStudentGrade(newStudent.getStudentGrade());
            existingStudent.setStudentAge(newStudent.getStudentAge());
            existingStudent.setPickupStop(newStudent.getPickupStop());
            existingStudent.setDropStop(newStudent.getDropStop());
            existingStudent.setParentFirstName(newStudent.getParentFirstName());
            existingStudent.setParentLastName(newStudent.getParentLastName());
            existingStudent.setMobNo(newStudent.getMobNo());
            existingStudent.setAddress(newStudent.getAddress());
            existingStudent.setEmail(newStudent.getEmail());
            existingStudent.setPassword(newStudent.getPassword());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }


    // Delete a student by ID
    public String deleteDetails(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student with ID " + id + " has been deleted.";
        }
        return "Student not found with ID " + id;
    }
    
    //send notification to next stop single student
//    public String notifyNextStop(String routeName, String currentStop) {
//        // Get all stops in order for the given route
//        List<BusStop> stops = busStopRepository.findByBusRouteRouteName(routeName);
//
//        // Find the current stop index
//        int currentIndex = -1;
//        for (int i = 0; i < stops.size(); i++) {
//            if (stops.get(i).getStopName().equals(currentStop)) {
//                currentIndex = i;
//                break;
//            }
//        }
//
//        // Check if there is a next stop
//        if (currentIndex == -1 || currentIndex + 1 >= stops.size()) {
//            return "No next stop available!";
//        }
//
//        // Get next stop name
//        String nextStop = stops.get(currentIndex + 1).getStopName();
//
//        // Fetch students at the next stop
//        List<Student> students = studentRepository.findByPickupStop(nextStop);
//
//        // Send email notification to students
//        for (Student student : students) {
//            sendEmail(student.getEmail(), nextStop);
//        }
//
//        return "Notification sent to students at " + nextStop;
//    }
    
//    public void notifyNextStopStudents(Long driverId, String currentStop) {
//        List<Object[]> students = studentRepository.findStudentsAtNextStop(driverId, currentStop);
//
//        if (students.isEmpty()) {
//            System.out.println("No students found at the next stop.");
//            return;
//        }
//
//        for (Object[] student : students) {
//            try {
//                String email = (String) student[1];
//                String studentName = student[2] + " " + student[3];
//                String pickupStop = (String) student[4];
//
//                if (email == null || email.trim().isEmpty()) {
//                    System.err.println("Skipping student with missing email.");
//                    continue;
//                }
//
//                System.out.println("Sending email to: " + email);
//
//                String subject = "School Bus Arrival Alert";
//                String message = String.format(
//                    "Dear Parent,\n\nThe school bus has arrived at the previous stop. " +
//                    "Please ensure your child, %s, is ready at the stop: %s.\n\n" +
//                    "Regards,\nSchool Transport Team",
//                    studentName, pickupStop
//                );
//
//                emailService.sendMail(email, subject, message);
//
//                System.out.println("Email sent successfully to: " + email);
//            } catch (Exception e) {
//                System.err.println("Failed to send email to: " + student[1]);
//                e.printStackTrace();
//            }
//        }
//    }
    
    public void notifyNextStopStudents(Long driverId) {
        List<Object[]> studentDataList = studentRepository.findStudentsAtNextStop(driverId);

        if (studentDataList.isEmpty()) {
            System.out.println("No students found at the next stop.");
            return;
        }

        for (Object[] studentData : studentDataList) {
            try {
                String pickupStop = (String) studentData[0]; // Pickup stop
                String studentIds = (String) studentData[1]; // Comma-separated IDs
                String studentFirstNames = (String) studentData[2]; // Comma-separated first names
                String studentLastNames = (String) studentData[3]; // Comma-separated last names
                String studentEmails = (String) studentData[4]; // Comma-separated emails

                if (studentEmails == null || studentEmails.trim().isEmpty()) {
                    System.err.println("Skipping students with missing email.");
                    continue;
                }

                // Convert comma-separated values to lists
                String[] firstNameArray = studentFirstNames.split(",");
                String[] lastNameArray = studentLastNames.split(",");
                String[] emailArray = studentEmails.split(",");

                for (int i = 0; i < emailArray.length; i++) {
                    String email = emailArray[i];
                    String studentName = firstNameArray[i] + " " + lastNameArray[i];

                    System.out.println("Sending email to: " + email);

                    String subject = "School Bus Arrival Alert";
                    String message = String.format(
                        "Dear Parent,\n\nThe school bus has arrived at the previous stop. " +
                        "Please ensure your child, %s, is ready at the stop: %s.\n\n" +
                        "Regards,\nSchool Transport Team",
                        studentName, pickupStop
                    );

                    emailService.sendMail(email, subject, message);

                    System.out.println("Email sent successfully to: " + email);
                }
            } catch (Exception e) {
                System.err.println("Error processing students for pickup stop.");
                e.printStackTrace();
            }
        }
    }


    
    private void sendEmail(String recipientEmail, String stopName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject("Your Bus is Approaching");
            helper.setText("Dear Parent/Student,<br><br>The school bus is on its way and has reached the previous stop. Your stop (" + stopName + ") is next. Please be ready!", true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
//    public List<Student> getAllStudentsWithBus() {
//        return studentRepository.findAll().stream()
//                .filter(student -> student.getPickupStop() != null && student.getPickupStop().getBusRoute() != null)
//                .collect(Collectors.toList());
//    }
    
    
    public List<Student> getStudentsByRoute(String routeName) {
        return studentRepository.findByBusRoute(routeName);
    }
    
    public List<Student> getStudentsByDriver(Long driverId) {
        return studentRepository.findStudentsByDriverId(driverId);
    }
    
    public List<Student> getStudentsByDriverReverse(Long driverId) {
        return studentRepository.findStudentsByDriverIdReverse(driverId);
    }

    public List<Student> findStudentsByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

}
