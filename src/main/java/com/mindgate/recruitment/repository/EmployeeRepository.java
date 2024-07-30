package com.mindgate.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mindgate.recruitment.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}