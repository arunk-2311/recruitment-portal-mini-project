package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplV1 implements EmployeeService{
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
	}
	@Override
	public Employee updateEmployeeStatus(int id, String status){
	    Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = optional.get();
        employee.setStatus(status);
        return employeeRepository.save(employee);
	}
	@Override
	public Employee create(Employee employee){
		return 	employeeRepository.save(employee);
	}
}
