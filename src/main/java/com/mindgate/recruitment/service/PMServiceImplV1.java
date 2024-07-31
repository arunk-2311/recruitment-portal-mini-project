package com.mindgate.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;
import com.mindgate.recruitment.exceptions.JobRequestNotFulFilledException;

public class PMServiceImplV1 implements PMService{
	
	@Autowired
	private JobRequestService jobRequestService;
	
	@Override
	public JobRequest fillJobRequest(int requestId, int num){
		
		try {
			JobRequest j = jobRequestService.updateJobRequestFilled(requestId, num);
			
			if(j.getPending() == 0) {
				jobRequestService.closeJobRequest(requestId);
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