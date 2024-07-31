package com.mindgate.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestInvalidLevelException;
import com.mindgate.recruitment.service.JobRequestService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class PMController {

	@Autowired
	private JobRequestService jobRequestService;

	// creates a job request
	@PutMapping(path = "/fillFromWorkBench/{id}/{count}")
	public ResponseEntity<Object> fillFromWorkBench(@PathVariable("id") int requestId,
			@PathVariable("count") int fillCount) {
		try {
			JobRequest j = jobRequestService.updateJobRequestFilled(requestId, fillCount);
			return ResponseEntity.status(201).body(j);
		} catch (JobFillOverflowException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

	@PutMapping(path = "/forwardToHR/{id}")
	public ResponseEntity<Object> forwardToHR(@PathVariable("id") int requestId) {
		try {
			JobRequest j = jobRequestService.updateJobRequestLevel(requestId, 1);
			return ResponseEntity.status(201).body(j);

		} catch (JobRequestInvalidLevelException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

}
