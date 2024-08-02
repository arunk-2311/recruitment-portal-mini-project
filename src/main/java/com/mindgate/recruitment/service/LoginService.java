package com.mindgate.recruitment.service;

import com.mindgate.recruitment.exceptions.*;
import com.mindgate.recruitment.beans.Login;

public interface LoginService {

	Login employeeLogin(Login login) throws LoginNotFoundException;

}