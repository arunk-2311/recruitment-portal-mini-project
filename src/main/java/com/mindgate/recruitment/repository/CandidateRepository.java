package com.mindgate.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgate.recruitment.beans.*;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
