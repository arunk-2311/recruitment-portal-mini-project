package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.*;
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
        candidate.setFinelSelection(status);
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

	
}
