package com.mindgate.recruitment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.repository.EmployeeRepository;

@Service
public class EmployeeServiceV1 implements EmployeeService{
	
	@Autowired
	public EmployeeRepository repository;
	
	@Override
	public List<Employee> fetchAllEmployees() {
		return repository.findAll();
	}
}
