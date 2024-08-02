package com.mindgate.recruitment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.service.EmployeeService;
import com.mindgate.recruitment.service.JobRequestService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tl")
public class TLController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private JobRequestService jobRequestService;
	
	//creates a job request
	@PostMapping(path = "/createJobRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createJobRequest(@RequestBody JobRequest jobRequest){
		JobRequest j = jobRequestService.store(jobRequest);	
		System.out.println(jobRequest);
		return ResponseEntity.status(201).body(j);
	}
	
	//showing active requests (Visible to TL & PM)
	@GetMapping(path = "/activeJobRequests/{id}")
	public ResponseEntity<Object> fetchActiveJobRequests(@PathVariable("id") int tlId){
		List<JobRequest> allJobRequests = jobRequestService.findByTeamLeaderId(tlId);
		List<JobRequest> activeJobRequests = allJobRequests.stream()
					.filter(jobRequest -> jobRequest.getJrLevel() == 0)
		            .collect(Collectors.toList());
		return ResponseEntity.status(200).body(activeJobRequests);
	}
	
	//showing closed requests
	@GetMapping(path = "/closedJobRequests/{id}")
	public ResponseEntity<Object> fetchClosedJobRequests(@PathVariable("id") int tlId){
		List<JobRequest> allJobRequests = jobRequestService.findByTeamLeaderId(tlId);
		List<JobRequest> closedJobRequests = allJobRequests.stream()
		            .filter(jobRequest -> jobRequest.getJrLevel() == 2)
		            .collect(Collectors.toList());
		return ResponseEntity.status(200).body(closedJobRequests);
	}
	
	
	//find all active employees
	@GetMapping(path = "/activeEmployees")
	public ResponseEntity<Object> fetchActiveEmployees(){
		List<Employee> list = employeeService.fetchAllEmployees();
		List<Employee> activeEmployees = list.stream()
		            .filter(employee -> "active".equalsIgnoreCase(employee.getStatus()))
		            .collect(Collectors.toList());
		return ResponseEntity.status(200).body(activeEmployees);
	}	
	
	@PutMapping(path = "/updateActiveEmployeesStatus/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateActiveEmployeesStatus(@PathVariable("id") int empId, @RequestBody Employee employee) {
	    Employee updatedEmployeeStatus = employeeService.updateEmployeeStatus(empId, employee.getStatus());
	    return ResponseEntity.status(200).body(updatedEmployeeStatus);
	}
	
	

}