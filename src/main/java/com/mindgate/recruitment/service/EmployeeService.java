package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.Employee;

public interface EmployeeService {

	List<Employee> fetchAllEmployees();

	Employee updateEmployeeStatus(int id, String status);

	Employee create(Employee employee);

}