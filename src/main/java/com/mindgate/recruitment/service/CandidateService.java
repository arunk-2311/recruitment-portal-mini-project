package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import com.mindgate.recruitment.beans.Candidate;

public interface CandidateService {

	List<Candidate> getAllCandidate();

	Optional<Candidate> getCandidateById(int candidateId);

	Candidate saveCandidate(Candidate candidate);

	void deleteCandidate(int candidateId);
	
	Candidate updateCandidateStatusFinal(int id, String status);
	
	Candidate updateCandidateStatusFirst(int id, String status);
	
	Candidate updateCandidateStatusThird(int id, String status);

}