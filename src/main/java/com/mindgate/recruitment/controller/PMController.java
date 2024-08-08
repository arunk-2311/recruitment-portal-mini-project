package com.mindgate.recruitment.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestInvalidLevelException;
import com.mindgate.recruitment.exceptions.JobRequestNotFoundException;
import com.mindgate.recruitment.exceptions.JobRequestNotFulFilledException;
import com.mindgate.recruitment.service.JobRequestService;
import com.mindgate.recruitment.service.PMService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pm")
public class PMController {

	@Autowired
	private JobRequestService jobRequestService;

	@Autowired
	private PMService pmService;

	// get all active job requests that have to be attended by PM
	@GetMapping(path = "/activeJobRequests")
	public ResponseEntity<Object> fetchAllActiveJobRequests() {
		return ResponseEntity.status(200).body(pmService.fetchAllActiveJobRequests());
	}
	
	// get all active job requests that have to be attended by PM
	@GetMapping(path = "/pendingJobRequests")
	public ResponseEntity<Object> fetchAllPendingJobRequests() {
		return ResponseEntity.status(200).body(pmService.fetchAllPendingJobRequests());
	}

	// get all active job requests that have to be attended by PM
	@GetMapping(path = "/closedJobRequests")
	public ResponseEntity<Object> fetchAllClosedJobRequests() {
		return ResponseEntity.status(200).body(pmService.fetchAllClosedJobRequests());
	}

	// creates a job request
	@PutMapping(path = "/fillFromWorkBench/{id}/{count}")
	public ResponseEntity<Object> fillFromWorkBench(@PathVariable("id") int requestId,
			@PathVariable("count") int fillCount) {
		try {
			JobRequest j = pmService.fillJobRequest(requestId, fillCount);
			return ResponseEntity.status(200).body(j);
		} catch (JobFillOverflowException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

	@PutMapping(path = "/forwardToHR/{id}")
	public ResponseEntity<Object> forwardToHR(@PathVariable("id") int requestId) {
		try {
			JobRequest j = jobRequestService.updateJobRequestLevel(requestId, 1);
			return ResponseEntity.status(200).body(j);

		} catch (JobRequestInvalidLevelException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
	
	@PutMapping(path = "/forceCloseReq/{jrId}")
	public ResponseEntity<Object> forceCloseReq(@PathVariable("jrId") int jrId) {
				try {
					JobRequest j = jobRequestService.forceCloseJobRequest(jrId);
					return ResponseEntity.status(200).body(j);
				} catch (JobRequestNotFoundException e) {
					e.printStackTrace();
					return ResponseEntity.status(404).body(e.getMessage());
				}
	}

}
