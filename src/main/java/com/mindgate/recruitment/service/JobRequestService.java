package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestInvalidLevelException;
import com.mindgate.recruitment.exceptions.JobRequestNotFoundException;
import com.mindgate.recruitment.exceptions.JobRequestNotFulFilledException;


public interface JobRequestService {
	//save method that takes profile
	JobRequest store(JobRequest jobRequest);
	
	//method that finds job requests by team leader id
	List<JobRequest> findByTeamLeaderId(int tlId);
	
	//method that finds job request by id
	JobRequest findByJobRequestId(int requestId) throws JobRequestNotFoundException;
	
	//method that updates job request's filled field
	JobRequest updateJobRequestFilled(int requestId, int fillCount) throws JobFillOverflowException;
	
	//method that updates the job request level
	JobRequest updateJobRequestLevel(int requestId, int level) throws JobRequestInvalidLevelException;
	
	//method that checks if the job request is fulfilled or not
	JobRequest closeJobRequest(int requestId) throws JobRequestNotFulFilledException;
	
<<<<<<< HEAD
	//method to update the Description column in Job Request
	JobRequest updateJobRequestDescription(int requestId, String description);
=======
	List<JobRequest> fetchAllActiveJobRequestsHR();
	
	JobRequest forceCloseJobRequest(int jrId) throws JobRequestNotFoundException;
>>>>>>> branch 'master' of https://github.com/arunk-2311/recruitment-portal-mini-project.git
}