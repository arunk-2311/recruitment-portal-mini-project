package com.mindgate.recruitment.service;

import com.mindgate.recruitment.beans.JobRequest;
import com.mindgate.recruitment.exceptions.JobFillOverflowException;

public interface PMService {
	JobRequest fillJobRequest(int requestId, int num) throws JobFillOverflowException;
}
