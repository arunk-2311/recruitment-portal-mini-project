package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.Employee;

public interface EmployeeService {

	// fetch all employees by calling findall method
	List<Employee> fetchAllEmployees();

}