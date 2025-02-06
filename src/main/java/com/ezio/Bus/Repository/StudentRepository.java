package com.ezio.Bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.Bus.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
