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

import com.ezio.Bus.Entity.Parent;
import com.ezio.Bus.Service.ParentService;

@Controller
@CrossOrigin(origins = "http://localhost:5173") 
public class ParentController {

	@Autowired
	private ParentService parentService;
	
	@PostMapping("/addParent")
	@ResponseBody
	public Parent addParent(@RequestBody Parent parent) {
		return parentService.addDetails(parent);
	}
	
	@GetMapping("/getParent")
	@ResponseBody
	public List<Parent> showData(){
		return parentService.getdetails();
	}
	
	@GetMapping("/getParent/{pid}")
	@ResponseBody
	public Parent showDataById(@PathVariable Long pid) {
		return parentService.getDetailsById(pid);
	}
	
	@PutMapping("/editParent/{pid}")
	@ResponseBody
	public Parent updateData(@RequestBody Parent parent, @PathVariable Long pid) {
		return parentService.editDetails(parent, pid);
	}
	
	@DeleteMapping("/deleteParent/{pid}")
	@ResponseBody
	public String removeData(@PathVariable Long pid) {
		return parentService.deleteDetails(pid);
	}
 }
