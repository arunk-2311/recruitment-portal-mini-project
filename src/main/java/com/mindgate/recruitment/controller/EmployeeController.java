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
	
	@GetMapping(path="/inactive/filter/{roleId}/{skillId}")
	public ResponseEntity<Object> filterInactiveEmployeesByRoleIdAndSkillId(@PathVariable("roleId") int roleId, @PathVariable("skillId") int skillId){
		return ResponseEntity.status(200).body(employeeService.filterInactiveEmployeesByRoleIdAndSkillId(roleId, skillId));
	}
	
	@GetMapping(path="/inactive/filter/name/{searchText}")
	public ResponseEntity<Object> filterInactiveEmployeesBySearchText(@PathVariable("searchText") String searchText){
		return ResponseEntity.status(200).body(employeeService.filterInactiveEmployeesByName(searchText));
	}
	
	/*
	 * 1) Filter all employees by status
	 * 2) "" by skillId
	 * 3) "" by roleId
	 * 4) "" by skillId and roleId
	 * 5) Search Employees by name
	 * */
	
	@GetMapping(path="/all/filter/roleAndSkill/{roleId}/{skillId}")
	public ResponseEntity<Object> filterAllEmployeesByRoleIdAndSkillId(@PathVariable("roleId") int roleId, @PathVariable("skillId") int skillId){
		return ResponseEntity.status(200).body(employeeService.filterAllEmployeesByRoleIdAndSkillId(roleId, skillId));
	}
	
	@GetMapping(path="/all/filter/role/{roleId}")
	public ResponseEntity<Object> filterAllEmployeesByRoleId(@PathVariable("roleId") int roleId){
		return ResponseEntity.status(200).body(employeeService.filterAllEmployeesByRoleId(roleId));
	}
	
	@GetMapping(path="/all/filter/skill/{skillId}")
	public ResponseEntity<Object> filterAllEmployeesBySkillId(@PathVariable("skillId") int skillId){
		return ResponseEntity.status(200).body(employeeService.filterAllEmployeesBySkillId(skillId));
	}
	
	@GetMapping(path="/all/filter/status/{status}")
	public ResponseEntity<Object> filterAllEmployeesByStatus(@PathVariable("status") String status){
		return ResponseEntity.status(200).body(employeeService.filterAllEmployeesByStatus(status));
	}
	
	@GetMapping(path="/all/filter/name/{searchText}")
	public ResponseEntity<Object> filterAllEmployeesBySearchText(@PathVariable("searchText") String searchText){
		return ResponseEntity.status(200).body(employeeService.filterAllEmployeesByName(searchText));
	}
	
	@GetMapping(path="/all/filter/nameAndStatus/{searchText}/{status}")
	public ResponseEntity<Object> filterAllEmployeesBySearchTextAndStatus(@PathVariable("searchText") String searchText, @PathVariable("status") String status){
		return ResponseEntity.status(200).body(employeeService.filterAllEmployeesByNameAndStatus(searchText, status));
	}
	
}
