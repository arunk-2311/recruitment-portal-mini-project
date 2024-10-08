package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.Employee;
import com.mindgate.recruitment.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplV1 implements EmployeeService {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Override
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployeeStatus(int id, String status) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = optional.get();
		employee.setStatus(status);
		return employeeRepository.save(employee);
	}

	@Override
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> fetchAllInactiveEmployess() {
		List<Employee> allEmployees = employeeRepository.findAll();
		List<Employee> inactiveEmployees = allEmployees.stream().filter(emp -> emp.getStatus().equals("inactive"))
				.collect(Collectors.toList());
		return inactiveEmployees;
	}
	
	@Override
	public List<Employee> filterInactiveEmployeesBySkillId(int skillId){
		List<Employee> inactiveEmployees = this.fetchAllInactiveEmployess();
		List<Employee> filteredEmployees = inactiveEmployees.stream().filter(emp -> (emp.getSkill1Id() == skillId) || (emp.getSkill2Id() == skillId) || (emp.getSkill3Id() == skillId)).collect(Collectors.toList());
		return filteredEmployees;
	}
	
	@Override
	public List<Employee> filterInactiveEmployeesByRoleId(int roleId){
		List<Employee> inactiveEmployees = this.fetchAllInactiveEmployess();
		List<Employee> filteredEmployees = inactiveEmployees.stream().filter(emp -> emp.getRoleId() == roleId).collect(Collectors.toList());
		return filteredEmployees;
	}

	@Override
	public List<Employee> filterInactiveEmployeesByRoleIdAndSkillId(int roleId, int skillId) {
		List<Employee> skillFilteredEmployees = this.filterInactiveEmployeesBySkillId(skillId);
		List<Employee> filteredEmployees = skillFilteredEmployees.stream().filter(emp -> emp.getRoleId() == roleId).collect(Collectors.toList());
		return filteredEmployees;
	}

	@Override
	public List<Employee> filterAllEmployeesBySkillId(int skillId) {
		List <Employee> allEmployees = this.fetchAllEmployees();
		List <Employee> filteredEmployees = allEmployees.stream().filter(emp -> (emp.getSkill1Id() == skillId) || (emp.getSkill2Id() == skillId) || (emp.getSkill3Id() == skillId)).collect(Collectors.toList());
		return filteredEmployees;
	}

	@Override
	public List<Employee> filterAllEmployeesByRoleId(int roleId) {
		List<Employee> allEmployees = this.fetchAllEmployees();
		List<Employee> filteredEmployees = allEmployees.stream().filter(emp -> emp.getRoleId() == roleId).collect(Collectors.toList());
		return filteredEmployees;
	}

	@Override
	public List<Employee> filterAllEmployeesByRoleIdAndSkillId(int roleId, int skillId) {
		List<Employee> skillFilteredEmployees = this.filterAllEmployeesBySkillId(skillId);
		List<Employee> filteredEmployees = skillFilteredEmployees.stream().filter(emp -> emp.getRoleId() == roleId).collect(Collectors.toList());
		return filteredEmployees;
	}

	@Override
	public List<Employee> filterAllEmployeesByStatus(String status) {
		List<Employee> allEmployees = this.fetchAllEmployees();
		List<Employee> filteredEmployees = allEmployees.stream().filter(emp -> emp.getStatus().equals(status)).collect(Collectors.toList());
 		return filteredEmployees;
	}

	@Override
	public List<Employee> filterAllEmployeesByName(String name) {
		List<Employee> allEmployees = this.fetchAllEmployees();
		List<Employee> filteredEmployees = allEmployees.stream().filter(emp -> emp.searchMatchForName(name)).collect(Collectors.toList());
 		return filteredEmployees;
	}

	@Override
	public List<Employee> filterInactiveEmployeesByName(String name) {
		List<Employee> inactiveEmployees = this.fetchAllInactiveEmployess();
		List<Employee> filteredEmployees = inactiveEmployees.stream().filter(emp -> emp.searchMatchForName(name)).collect(Collectors.toList());
		return filteredEmployees;
	}

	@Override
	public List<Employee> filterAllEmployeesByNameAndStatus(String name, String status) {
		List<Employee> nameFilteredEmployees = this.filterAllEmployeesByName(name);
		List<Employee> filteredEmployees = nameFilteredEmployees.stream().filter(emp -> emp.getStatus().equals(status)).collect(Collectors.toList());
 		return filteredEmployees;
	}

}
