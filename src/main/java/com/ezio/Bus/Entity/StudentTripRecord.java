package com.ezio.Bus.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentTripRecord {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tripDate;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private AssignedBus bus;
    
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    
    @ManyToOne
    @JoinColumn(name = "pickup_stop_id")
    private BusStop pickupStop;
    
    private LocalTime pickupTime;
    
    @ManyToOne
    @JoinColumn(name = "drop_stop_id")
    private BusStop dropStop;
    
    private LocalTime dropTime;
    
    // New fields to track student absence
    private boolean pickupAbsent; // True if student was absent for pickup
    private boolean dropAbsent;   // True if student was absent for drop

	public StudentTripRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public StudentTripRecord(Long id, LocalDate tripDate, Student student, AssignedBus bus, Driver driver,
			BusStop pickupStop, LocalTime pickupTime, BusStop dropStop, LocalTime dropTime, boolean pickupAbsent,
			boolean dropAbsent) {
		super();
		this.id = id;
		this.tripDate = tripDate;
		this.student = student;
		this.bus = bus;
		this.driver = driver;
		this.pickupStop = pickupStop;
		this.pickupTime = pickupTime;
		this.dropStop = dropStop;
		this.dropTime = dropTime;
		this.pickupAbsent = pickupAbsent;
		this.dropAbsent = dropAbsent;
	}

	

	public boolean isPickupAbsent() {
		return pickupAbsent;
	}


	public void setPickupAbsent(boolean pickupAbsent) {
		this.pickupAbsent = pickupAbsent;
	}


	public boolean isDropAbsent() {
		return dropAbsent;
	}


	public void setDropAbsent(boolean dropAbsent) {
		this.dropAbsent = dropAbsent;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getTripDate() {
		return tripDate;
	}

	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public AssignedBus getBus() {
		return bus;
	}

	public void setBus(AssignedBus bus) {
		this.bus = bus;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public BusStop getPickupStop() {
		return pickupStop;
	}

	public void setPickupStop(BusStop pickupStop) {
		this.pickupStop = pickupStop;
	}

	public LocalTime getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(LocalTime pickupTime) {
		this.pickupTime = pickupTime;
	}

	public BusStop getDropStop() {
		return dropStop;
	}

	public void setDropStop(BusStop dropStop) {
		this.dropStop = dropStop;
	}

	public LocalTime getDropTime() {
		return dropTime;
	}

	public void setDropTime(LocalTime dropTime) {
		this.dropTime = dropTime;
	}
    
    
    
}
