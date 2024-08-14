package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.*;
import com.mindgate.recruitment.exceptions.CandidateNotFoundException;
import com.mindgate.recruitment.repository.*;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public List<Candidate> getAllCandidate(){
		return candidateRepository.findAll();
	}
	
	@Override
	public Optional<Candidate> getCandidateById(int candidateId){
		return candidateRepository.findById(candidateId);
	}
	
	@Override
	public Candidate saveCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	@Override
	public void deleteCandidate(int candidateId) {
		candidateRepository.deleteById(candidateId);
	}
	
	@Override
	public Candidate updateCandidateStatusFinal(int id, String status){
	    Optional<Candidate> optional = candidateRepository.findById(id);
        Candidate candidate = optional.get();
        candidate.setFinalSelection(status);
//        candidate.setConfirmationStatus(status);
        return candidateRepository.save(candidate);
	}
	@Override
	public Candidate updateCandidateStatusFirst(int id, String status){
	    Optional<Candidate> optional = candidateRepository.findById(id);
        Candidate candidate = optional.get();
        candidate.setSelectedForInterview(status);
        return candidateRepository.save(candidate);
	}
	@Override
	public Candidate updateCandidateStatusThird(int id, String status){
	    Optional<Candidate> optional = candidateRepository.findById(id);
        Candidate candidate = optional.get();
        candidate.setConfirmationStatus(status);
        return candidateRepository.save(candidate);
	}

	@Override
	public void sendTechnicalInterviewEmail(String interviewMeetLink, String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Technical Interview Invitation");
		message.setText("Dear Candidate,\n\n" 
				+ "Congratulations!! \n\n"
				+ "You are invited to attend the technical interview tomorrow. \n"
				+ "Please join the meeting using the following link:\n"
				+ interviewMeetLink + "\n\n" + "The interview is scheduled at 9 AM.\n\n" + "Best regards,\n"
				+ "MGS Recruitment Team");

		mailSender.send(message);
	}

	@Override
	public void sendHRConfirmationEmail(String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Final Selection Mail!!");
		message.setText("Dear Candidate,\n\n" 
				+ "Congratulations!! \n\n"
				+ "You are selected for your applied role and invited to be a part of MGS Family.  \n"
				+ "Please submit the following documents for your successful onboarding:\n\n" 
				+ "1. 10th Marksheet \n"
				+ "2. 12th Marksheet \n"
				+ "3. ID Proof \n"
				+ "4. Bachelor's Transcript \n"
				+ "5. Scanned Passport Photo \n\n"
				+ "Best regards,\n"
				+ "MGS Recruitment Team");

		mailSender.send(message);
	}
	
	@Override
	public List<Candidate> selectedCandidatesList() throws CandidateNotFoundException {
		List<Candidate> allCandidates = this.getAllCandidate();
		List<Candidate> finalCandidatelist = allCandidates.stream()
				.filter(candidates -> ("y".equals(candidates.getConfirmationStatus())
						&& (candidates.getFinalSelection() == null)))
				.collect(Collectors.toList());
	return finalCandidatelist;
	}
	
	@Override
	public List<Candidate> filterAllCandidatesByName(String name) {
		List<Candidate> allCandidates = this.getAllCandidate();
		List<Candidate> filteredCandidates = allCandidates.stream().filter(cand->cand.searchMatchForNameCan(name)).collect(Collectors.toList());
		return filteredCandidates;
	}

	@Override
	public List<Candidate> filterFinalCandidatesSkillId(int skillId) throws CandidateNotFoundException {
		List<Candidate> finalCandidates = this.selectedCandidatesList();
		List<Candidate> filteredCandidates = finalCandidates.stream().filter(cand->(cand.getSkill1Id()== skillId)||(cand.getSkill2Id()== skillId)||(cand.getSkill3Id()== skillId)).collect(Collectors.toList());
		return filteredCandidates;
	}

	@Override
	public List<Candidate> filterFinalCandidatesByRoleId(int roleId) throws CandidateNotFoundException {
		List<Candidate> finalCandidates = this.selectedCandidatesList();
		List<Candidate> filteredCandidates = finalCandidates.stream().filter(cand->(cand.getRoleId()== roleId)).collect(Collectors.toList());
		return filteredCandidates;
	}

	@Override
	public List<Candidate> filterFinalCandidatesByRoleIdAndSkillId(int roleId, int skillId) throws CandidateNotFoundException {
		List<Candidate> skillFilteredCandidates=this.filterFinalCandidatesSkillId(skillId);
		List<Candidate> filteredEmployees=skillFilteredCandidates.stream().filter(cand->cand.getRoleId()==roleId).collect(Collectors.toList());
		return filteredEmployees;
	}
	
	@Override
	public void meetLinkById(int id,String meetLink) {
		Optional<Candidate> findCandidate=candidateRepository.findById(id);
		Candidate result=findCandidate.get();
		result.setInterviewMeetLink(meetLink);
		candidateRepository.save(result);
	}
	
	@Override
	public void reset(int id) {
		Optional<Candidate> findCandidate=candidateRepository.findById(id);
		Candidate result=findCandidate.get();
		result.setAssessmentStatus(null);
		result.setConfirmationStatus(null);
		result.setFinalSelection(null);
		result.setSelectedForInterview(null);
		candidateRepository.save(result);
	}
}
