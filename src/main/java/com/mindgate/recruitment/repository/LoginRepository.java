package com.mindgate.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgate.recruitment.beans.Login;



public interface LoginRepository extends JpaRepository<Login, Integer> {}