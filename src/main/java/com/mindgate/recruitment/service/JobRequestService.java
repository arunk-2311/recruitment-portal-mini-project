package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestNotFoundException;


public interface JobRequestService {
	//save method that takes profile
	JobRequest store(JobRequest jobRequest);
	
	//method that finds job requests by team leader id
	List<JobRequest> findByTeamLeaderId(int tlId);
	
	//method that finds job request by id
	JobRequest findByJobRequestId(int requestId) throws JobRequestNotFoundException;
	
	//method that updates job request's filled field
	JobRequest UpdateJobRequestFilled(int requestId, int fillCount) throws JobFillOverflowException;
}