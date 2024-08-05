package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.*;
import com.mindgate.recruitment.repository.*;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
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
}
