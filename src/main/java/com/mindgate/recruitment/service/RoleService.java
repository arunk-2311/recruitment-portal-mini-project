package com.mindgate.recruitment.service;

import java.util.List;
import com.mindgate.recruitment.beans.*;
import com.mindgate.recruitment.exceptions.*;

public interface RoleService {
	Role employeeRole(Login loginInfo) throws RoleNotFoundException;
	List<Role> getAllRoles();
	Role getRoleById(int id) throws RoleNotFoundException;
}