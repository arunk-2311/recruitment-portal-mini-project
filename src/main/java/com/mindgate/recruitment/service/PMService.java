package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;

public interface PMService {
	JobRequest fillJobRequest(int requestId, int num) throws JobFillOverflowException;
	// All job requests that have to be attended by PM
	List<JobRequest> fetchAllActiveJobRequests();
	// All job requests that have been transferred to HR
	List<JobRequest> fetchAllPendingJobRequests();
	// All job requests that have been fulfilled
	List<JobRequest> fetchAllClosedJobRequests();
}
