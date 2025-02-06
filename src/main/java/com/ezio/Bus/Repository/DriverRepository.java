package com.ezio.Bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.Bus.Entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver findByDriverEmailAndDriverPassword(String driverEmail, String driverPassword);
	
}
