package com.mindgate.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgate.recruitment.beans.JobRequest;

public interface JobRequestRepository extends JpaRepository<JobRequest, Integer> {	
}