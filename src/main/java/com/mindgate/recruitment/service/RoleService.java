package com.mindgate.recruitment.service;

import com.mindgate.recruitment.service.*;
import com.mindgate.recruitment.beans.*;
import com.mindgate.recruitment.exceptions.*;

public interface RoleService {

	Role employeeRole(Login loginInfo) throws RoleNotFoundException;

}