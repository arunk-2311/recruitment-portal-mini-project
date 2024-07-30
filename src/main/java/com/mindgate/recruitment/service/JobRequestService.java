package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.JobRequest;


public interface JobRequestService {
	//save method that takes profile
	JobRequest store(JobRequest jobRequest);
	List<JobRequest> findByTeamLeaderId(int tlId);
}