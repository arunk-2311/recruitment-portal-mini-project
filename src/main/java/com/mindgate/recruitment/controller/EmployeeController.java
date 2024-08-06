package com.mindgate.recruitment.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	// find all employees
	@GetMapping(path = "/")
	public ResponseEntity<Object> fetchEmployees() {
		List<Employee> list = employeeService.fetchAllEmployees();
		return ResponseEntity.status(200).body(list);
	}

	@GetMapping(path = "/inactive")
	public ResponseEntity<Object> fetchInactiveEmployees() {
		List<Employee> empList = employeeService.fetchAllEmployees();
		List<Employee> inactiveEmployees = empList.stream()
				.filter((employee) -> "inactive".equalsIgnoreCase(employee.getStatus())).collect(Collectors.toList());
		return ResponseEntity.status(200).body(inactiveEmployees);
	}

	@PutMapping(path = "/activate/{id}")
	public ResponseEntity<Object> activateEmployeeStatus(@PathVariable("id") int id) {
		return ResponseEntity.status(200).body(employeeService.updateEmployeeStatus(id, "active"));
	}

	//	For filtering workbench employees
	@GetMapping(path="/inactive/filter/skill/{skillId}")
	public ResponseEntity<Object> filterInactiveEmployeesBySkillId(@PathVariable("skillId") int skillId) {
		return ResponseEntity.status(200).body(employeeService.filterInactiveEmployeesBySkillId(skillId));
	}
	
	@GetMapping(path="/inactive/filter/role/{roleId}")
	public ResponseEntity<Object> filterInactiveEmployeesByRoleId(@PathVariable("roleId") int roleId) {
		return ResponseEntity.status(200).body(employeeService.filterInactiveEmployeesByRoleId(roleId));
	}
}
