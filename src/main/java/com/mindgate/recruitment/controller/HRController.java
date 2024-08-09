package com.mindgate.recruitment.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Candidate;
import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.CandidateNotFoundException;
import com.mindgate.recruitment.service.CandidateService;
import com.mindgate.recruitment.service.JobRequestService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hr")
public class HRController {
	@Autowired
	private CandidateService candidateService;

	@Autowired
	private JobRequestService jobRequestService;

	@GetMapping(path = "/activeJobRequestsHR")
	public ResponseEntity<Object> fetchAllActiveJobRequests() {
		return ResponseEntity.status(200).body(jobRequestService.fetchAllActiveJobRequestsHR());
	}

	@GetMapping(path = "/final")
	public ResponseEntity<Object> selectedCandidates() throws CandidateNotFoundException {
		List<Candidate> allCandidates = candidateService.getAllCandidate();
		List<Candidate> finalCandidatelist = allCandidates.stream()
				.filter(candidates -> ("y".equals(candidates.getConfirmationStatus())
						&& (candidates.getFinalSelection() == null)))
				.collect(Collectors.toList());
		return ResponseEntity.status(200).body(finalCandidatelist);
	}

	@GetMapping(path = "/listOfAllCandidates")
	public ResponseEntity<Object> listOfAllCandidates() throws CandidateNotFoundException {
		List<Candidate> allCandidates = candidateService.getAllCandidate();
		List<Candidate> finalCandidatelist = allCandidates.stream()
				.filter(candidates -> (candidates.getFinalSelection() == null)).collect(Collectors.toList());
		return ResponseEntity.status(200).body(finalCandidatelist);
	}
	
	@PutMapping(path = "/toInterviewer/{id}")
	public ResponseEntity<Object> toInterviewer(@PathVariable("id") int id) {
		return ResponseEntity.status(200).body(candidateService.updateCandidateStatusFirst(id, "y"));
	}

	@PutMapping(path = "/toSelected/{id}")
	public ResponseEntity<Object> toSelected(@PathVariable("id") int id) {
		return ResponseEntity.status(200).body(candidateService.updateCandidateStatusThird(id, "y"));
	}
	
	@PutMapping(path = "/toSelectedR/{id}")
	public ResponseEntity<Object> toSelectedR(@PathVariable("id") int id) {
		return ResponseEntity.status(200).body(candidateService.updateCandidateStatusThird(id, "n"));
	}
	
	@PutMapping(path = "/readyforhire/{id}")
	public ResponseEntity<Object> activateCanddate(@PathVariable("id") int id) {
		return ResponseEntity.status(200).body(candidateService.updateCandidateStatusFinal(id, "y"));
	}

	@PostMapping(path = "/newcandidate")
	public ResponseEntity<Object> createNewCandidate(@RequestBody Candidate candidate) {
		return ResponseEntity.status(200).body(candidateService.saveCandidate(candidate));
	}
}