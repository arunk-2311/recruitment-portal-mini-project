package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.Employee;

public interface EmployeeService {
	List<Employee> fetchAllEmployees();
	Employee updateEmployeeStatus(int id, String status);
	Employee create(Employee employee);
	List<Employee> fetchAllInactiveEmployess();
	List<Employee> filterAllEmployeesBySkillId(int skillId);
	List<Employee> filterAllEmployeesByRoleId(int roleId);
	List<Employee> filterAllEmployeesByRoleIdAndSkillId(int roleId, int skillId);
	List<Employee> filterAllEmployeesByStatus(String status);
	List<Employee> filterAllEmployeesByName(String name);
	List<Employee> filterInactiveEmployeesBySkillId(int skillId);
	List<Employee> filterInactiveEmployeesByRoleId(int roleId);
	List<Employee> filterInactiveEmployeesByRoleIdAndSkillId(int roleId, int skillId);
}