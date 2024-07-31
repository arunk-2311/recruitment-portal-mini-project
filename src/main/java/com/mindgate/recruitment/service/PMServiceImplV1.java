package com.mindgate.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;

public class PMServiceImplV1 implements PMService{
	
	@Autowired
	private JobRequestService jobRequestService;
	
	@Override
	public JobRequest fillJobRequest(int requestId, int num){
		
		try {
			return jobRequestService.updateJobRequestFilled(requestId, num);
		} catch (JobFillOverflowException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
