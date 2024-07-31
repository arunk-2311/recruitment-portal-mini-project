package com.mindgate.recruitment.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestInvalidLevelException;
import com.mindgate.recruitment.exceptions.JobRequestNotFoundException;
import com.mindgate.recruitment.exceptions.JobRequestNotFulFilledException;
import com.mindgate.recruitment.repository.JobRequestRepository;

@Service
public class JobRequestServiceImpl implements JobRequestService {

	@Autowired
	private JobRequestRepository jobRequestRepository;

	// save method that takes profile
	@Override
	public JobRequest store(JobRequest jobRequest) {
		return jobRequestRepository.save(jobRequest);
	}

	@Override
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
	public JobRequest updateJobRequestFilled(int requestId, int fillCount) throws JobFillOverflowException {

		try {
			JobRequest jobRequest = this.findByJobRequestId(requestId);

			int filled = jobRequest.getFilled();
			int vacancies = jobRequest.getVacancies();

			if (filled + fillCount <= vacancies) {
				jobRequest.setFilled(filled + fillCount);
			} else {
				throw new JobFillOverflowException("Job Request Fill exceeds vacancies!");
			}
			
			jobRequest.setPending(vacancies - (filled + fillCount));

			JobRequest updatedJobRequest = jobRequestRepository.save(jobRequest);
			return updatedJobRequest;
		} catch (JobRequestNotFoundException e) {
			throw new JobFillOverflowException(e.getMessage());
		}

	}

	@Override
	public JobRequest updateJobRequestLevel(int requestId, int level) throws JobRequestInvalidLevelException {
		// TODO Auto-generated method stub
		try {
			JobRequest jobRequest = this.findByJobRequestId(requestId);

			int requestlvl = jobRequest.getJrLevel();

			if (level > requestlvl && level < 3) {
				jobRequest.setJrLevel(level);
			} else {
				throw new JobRequestInvalidLevelException("Invalid job request level!");
			}

			JobRequest updatedJobRequest = jobRequestRepository.save(jobRequest);
			return updatedJobRequest;

		} catch (JobRequestNotFoundException e) {
			throw new JobRequestInvalidLevelException(e.getMessage());
		}
	}
	
	@Override
	public JobRequest closeJobRequest(int requestId) throws JobRequestNotFulFilledException {
		
		// TODO Auto-generated method stub
		try {
			JobRequest jobRequest = this.findByJobRequestId(requestId);
			
			int requestlvl = jobRequest.getJrLevel();
			
			if(requestlvl == 2) {
				throw new JobRequestNotFulFilledException("Job request already fulfilled!");
			}
			
			int pending = jobRequest.getPending();
			
			if(pending == 0) {
				// fulfilled
				jobRequest.setJrLevel(2);
			}else {
				throw new JobRequestNotFulFilledException("Job vacancies are not filled yet!");
			}
			
			return jobRequest;

		} catch (JobRequestNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}