package com.ezio.Bus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.Student;
import com.ezio.Bus.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
    private StudentRepository studentRepository;

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
    public Student editDetails(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null; // If the student does not exist
    }

    // Delete a student by ID
    public String deleteDetails(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student with ID " + id + " has been deleted.";
        }
        return "Student not found with ID " + id;
    }
}
