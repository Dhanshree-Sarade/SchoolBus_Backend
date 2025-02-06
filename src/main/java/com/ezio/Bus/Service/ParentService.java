package com.ezio.Bus.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.Bus.Entity.Parent;
import com.ezio.Bus.Repository.ParentRepository;

@Service
public class ParentService {

	@Autowired
	private ParentRepository parentRepository;
	
	public Parent addDetails(Parent parent) {
		return parentRepository.save(parent);
	}
	
	public List<Parent> getdetails(){
		return parentRepository.findAll();
	}
	
	public Parent getDetailsById(Long id) {
		return parentRepository.findById(id).orElse(null);
	}
	
	public Parent editDetails(Parent newParent, Long pid) {
	    return parentRepository.findById(pid).map(existingParent -> {
	        existingParent.setParentFirstName(newParent.getParentFirstName());
	        existingParent.setParentLastName(newParent.getParentLastName());
	        existingParent.setCandidateName(newParent.getCandidateName());
	        existingParent.setAddress(newParent.getAddress());
	        existingParent.setMobNo(newParent.getMobNo());
	        existingParent.setEmail(newParent.getEmail());
	        existingParent.setPassword(newParent.getPassword());
	        return parentRepository.save(existingParent);
	    }).orElseThrow(() -> new RuntimeException("Parent not found with id: " + pid));
	}
	
	public String deleteDetails(Long id) {
		parentRepository.deleteById(id);
		return "Parent Record deleted successfully...";
	}
}
