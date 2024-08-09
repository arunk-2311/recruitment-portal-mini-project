package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindgate.recruitment.beans.Candidate;

public interface CandidateService {

	List<Candidate> getAllCandidate();

	Optional<Candidate> getCandidateById(int candidateId);

	Candidate saveCandidate(Candidate candidate);

	void deleteCandidate(int candidateId);
	
	Candidate updateCandidateStatusFinal(int id, String status);
	
	Candidate updateCandidateStatusFirst(int id, String status);
	
	void sendTechnicalInterviewEmail(String interviewMeetLink, String email);
	
	void sendHRConfirmationEmail(String email);
	
	Candidate updateCandidateStatusThird(int id, String status);

}