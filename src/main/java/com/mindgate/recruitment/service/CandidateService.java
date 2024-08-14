package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindgate.recruitment.beans.Candidate;
import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.exceptions.CandidateNotFoundException;

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
	
	List<Candidate> selectedCandidatesList() throws CandidateNotFoundException;
	
	List<Candidate> filterAllCandidatesByName(String name);
	
	List<Candidate> filterFinalCandidatesSkillId(int skillId) throws CandidateNotFoundException;
	
	List<Candidate> filterFinalCandidatesByRoleId(int roleId) throws CandidateNotFoundException;
	
	List<Candidate> filterFinalCandidatesByRoleIdAndSkillId(int roleId, int skillId) throws CandidateNotFoundException;

	void meetLinkById(int id,String meetLink);
	
	void reset(int id);
}