package com.mindgate.recruitment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgate.recruitment.beans.Login;



public interface LoginRepository extends JpaRepository<Login, Integer> {
	 Optional<Login> findByEmail(String email);
}