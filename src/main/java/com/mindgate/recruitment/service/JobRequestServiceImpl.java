package com.mindgate.recruitment.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestNotFoundException;
import com.mindgate.recruitment.repository.JobRequestRepository;

@Service
public class JobRequestServiceImpl implements JobRequestService {

	@Autowired
	private JobRequestRepository jobRequestRepository;

	// save method that takes profile
	public JobRequest store(JobRequest jobRequest) {
		return jobRequestRepository.save(jobRequest);
	}

	public List<JobRequest> findByTeamLeaderId(int tlId) {
		List<Integer> listOfTlId = Collections.singletonList(tlId);
		List<JobRequest> allJobRequests = jobRequestRepository.findAll();
		List<JobRequest> specificTlJobRequests = allJobRequests.stream()
	            .filter(jobRequest -> jobRequest.getTlId() == tlId)
	            .collect(Collectors.toList());
		return specificTlJobRequests;
	}

	@Override
	public JobRequest findByJobRequestId(int requestId) throws JobRequestNotFoundException {
		// TODO Auto-generated method stub
		Optional<JobRequest> optional = jobRequestRepository.findById(requestId);

		return optional
				.orElseThrow(() -> new JobRequestNotFoundException("Job request with id " + requestId + " not found"));
	}

	@Override
	public JobRequest UpdateJobRequestFilled(int requestId, int fillCount) throws JobFillOverflowException {
		
		try {
			JobRequest jobRequest = this.findByJobRequestId(requestId);
			
			int filled = jobRequest.getFilled();
			int vacancies = jobRequest.getVacancies();
			
			if(filled + fillCount <= vacancies) {
				jobRequest.setFilled(filled + fillCount);
				jobRequest.setPending(vacancies - (filled + fillCount));
			}else {
				throw new JobFillOverflowException("Job Request Fill exceeds vacancies!");
			}
			
			JobRequest updatedJobRequest = jobRequestRepository.save(jobRequest);
			return updatedJobRequest;
		} catch (JobRequestNotFoundException e) {
			throw new JobFillOverflowException(e.getMessage());
		}
		
	}

}