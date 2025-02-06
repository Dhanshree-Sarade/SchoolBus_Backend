package com.ezio.Bus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezio.Bus.Entity.Student;
import com.ezio.Bus.Service.StudentService;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

	@Autowired
    private StudentService studentService;

    // Add new student
    @PostMapping("/addStudent")
    @ResponseBody
    public Student addStudent(@RequestBody Student student) {
        return studentService.addDetails(student);
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
    @PutMapping("/editStudent")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.editDetails(student);
    }

    // Delete student by ID
    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteDetails(id);
    }
	
}
