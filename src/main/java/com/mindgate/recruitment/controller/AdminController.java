package com.mindgate.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.service.EmployeeServiceImplV1;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/employee/login/admin")
public class AdminController {
	
	@Autowired
	private EmployeeServiceImplV1 employeeServiceImp;
	
	@PostMapping(path="/create",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createProfile(@RequestBody Employee employee){
		Employee emp = employeeServiceImp.create(employee);
		return ResponseEntity.status(201).body(emp);
	}
}
