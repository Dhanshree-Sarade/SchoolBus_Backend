package com.ezio.Bus.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.StudentTripRecord;
import com.ezio.Bus.Repository.StudentTripRecordRepository;

@Service
public class StudentTripRecordService {

	@Autowired
	private StudentTripRecordRepository studentTripRecordRepository;
	
	
	public List<StudentTripRecord> getAllTrips() {
        return studentTripRecordRepository.findAll();
    }

    public List<StudentTripRecord> getTripsByDate(LocalDate date) {
        return studentTripRecordRepository.findByTripDate(date);
    }

    public StudentTripRecord saveTripRecord(StudentTripRecord tripRecord) {
        return studentTripRecordRepository.save(tripRecord);
    }
}
