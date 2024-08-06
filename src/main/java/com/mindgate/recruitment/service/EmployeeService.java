package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.Employee;

public interface EmployeeService {

	// fetch all employees by calling find all method
	List<Employee> fetchAllEmployees();
	Employee updateEmployeeStatus(int id, String status);
	Employee create(Employee employee);
	List<Employee> fetchAllInactiveEmployess();
	List<Employee> filterInactiveEmployeesBySkillId(int skillId);
	List<Employee> filterInactiveEmployeesByRoleId(int roleId);
	List<Employee> filterInactiveEmployeesByRoleIdAndSkillId(int skillId, int roleId);
}