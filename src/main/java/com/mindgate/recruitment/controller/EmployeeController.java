package com.mindgate.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class EmployeeController {

	@Autowired
	public EmployeeService service;

	// find all employees
	@GetMapping(path = "/employees")
	public ResponseEntity<Object> fetchProfiles() {
		List<Employee> list = service.fetchAllEmployees();
		return ResponseEntity.status(200).body(list);
	}

}
