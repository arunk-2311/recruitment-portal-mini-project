package com.mindgate.recruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestNotFulFilledException;
import com.mindgate.recruitment.repository.JobRequestRepository;

@Service
public class PMServiceImplV1 implements PMService {

	@Autowired
	private JobRequestService jobRequestService;
	
	@Autowired
	private JobRequestRepository jobRequestRepository;

	@Override
	public List<JobRequest> fetchAllActiveJobRequests() {
		List<JobRequest> allJobRequests = jobRequestRepository.findAll();
		List<JobRequest> activeJobRequests = allJobRequests.stream()
	            .filter(jobRequest -> jobRequest.getJrLevel() == 0)
	            .collect(Collectors.toList());
		return activeJobRequests;
	}
	
	@Override
	public List<JobRequest> fetchAllPendingJobRequests() {
		List<JobRequest> allJobRequests = jobRequestRepository.findAll();
		List<JobRequest> activeJobRequests = allJobRequests.stream()
	            .filter(jobRequest -> jobRequest.getJrLevel() == 1)
	            .collect(Collectors.toList());
		return activeJobRequests;
	}
	
	@Override
	public List<JobRequest> fetchAllClosedJobRequests() {
		List<JobRequest> allJobRequests = jobRequestRepository.findAll();
		List<JobRequest> activeJobRequests = allJobRequests.stream()
	            .filter(jobRequest -> jobRequest.getJrLevel() == 2)
	            .collect(Collectors.toList());
		return activeJobRequests;
	}

	@Override
	public JobRequest fillJobRequest(int requestId, int num) {

		try {
			JobRequest j = jobRequestService.updateJobRequestFilled(requestId, num);

			if (j.getPending() == 0) {
				j = jobRequestService.closeJobRequest(requestId);
			}

			return j;
		} catch (JobFillOverflowException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (JobRequestNotFulFilledException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;
	}
}