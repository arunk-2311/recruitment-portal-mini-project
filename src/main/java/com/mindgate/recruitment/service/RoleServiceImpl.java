package com.mindgate.recruitment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.service.*;
import com.mindgate.recruitment.beans.*;
import com.mindgate.recruitment.exceptions.*;
import com.mindgate.recruitment.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role employeeRole(Login loginInfo) throws RoleNotFoundException {
		Optional<Role> roleInfo = roleRepository.findById(loginInfo.getRoleId());
		return roleInfo.orElseThrow(() -> new RoleNotFoundException("Role Id " + loginInfo.getRoleId() + " not found"));
	}
}