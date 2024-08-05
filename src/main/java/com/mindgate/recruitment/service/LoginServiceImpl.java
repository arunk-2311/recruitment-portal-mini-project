package com.mindgate.recruitment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.Login;
import com.mindgate.recruitment.exceptions.LoginNotFoundException;
import com.mindgate.recruitment.repository.LoginRepository;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Login employeeLogin(Login login) throws LoginNotFoundException {
		Optional<Login> loginInfo = loginRepository.findById(login.getEmployeeId());
		return loginInfo.orElseThrow(() -> new LoginNotFoundException("Employee " + login.getEmployeeId() + " not found"));
	}
}
