package com.mindgate.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestNotFoundException;
import com.mindgate.recruitment.service.EmployeeService;
import com.mindgate.recruitment.service.JobRequestService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class PMController {

	@Autowired
	private JobRequestService jobRequestService;

	// creates a job request
	@PutMapping(path = "/fillfromworkbench/{id}/{count}")
	public ResponseEntity<Object> fillFromWorkBench(@PathVariable("id") int id, @PathVariable("count") int fillCount) {		
		try {
			JobRequest j = jobRequestService.UpdateJobRequestFilled(id, fillCount);
			return ResponseEntity.status(201).body(j);
		} catch (JobFillOverflowException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
	
}
