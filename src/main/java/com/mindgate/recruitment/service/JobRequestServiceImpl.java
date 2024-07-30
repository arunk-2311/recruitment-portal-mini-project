package com.mindgate.recruitment.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.repository.JobRequestRepository;

@Service
public class JobRequestServiceImpl implements JobRequestService {
	
		@Autowired
		private JobRequestRepository jobRequestRepository;
		
		//save method that takes profile
		public JobRequest store(JobRequest jobRequest){
			return jobRequestRepository.save(jobRequest);
		}
		
		public List<JobRequest> findByTeamLeaderId(int tlId){
			List<Integer> listof_tlId = Collections.singletonList(tlId);
			return jobRequestRepository.findAllById(listof_tlId);
		}
		
		
}