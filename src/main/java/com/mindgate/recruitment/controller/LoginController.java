package com.mindgate.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Login;
import com.mindgate.recruitment.beans.LoginReturnResult;
import com.mindgate.recruitment.beans.Role;
import com.mindgate.recruitment.exceptions.LoginNotFoundException;
import com.mindgate.recruitment.exceptions.RoleNotFoundException;
import com.mindgate.recruitment.service.LoginService;
import com.mindgate.recruitment.service.PasswordResetService;
import com.mindgate.recruitment.service.RoleService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/employee")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
    private PasswordResetService passwordResetService;
	@PostMapping(path = "/login")
	public ResponseEntity<Object> checkLogin(@RequestBody Login login) throws RoleNotFoundException {
		try {

			if (login.getEmployeeId() == 1001 && login.getPassword().equals("Admin@123")) {
				return ResponseEntity.status(200).body(new LoginReturnResult(true, "admin",1001));
			}
			Login log = loginService.employeeLogin(login);
			if (log.getPassword().equals(login.getPassword())) {
				Role role=roleService.employeeRole(log);
				return ResponseEntity.status(200).body(new LoginReturnResult(true, role.getEmpRole(), log.getEmployeeId()));
			} else
				return ResponseEntity.status(401).body("Password Incorrect");
		} catch (LoginNotFoundException e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}
   @PostMapping(path = "/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestParam String email) {
        try {
            passwordResetService.initiatePasswordReset(email);
            return ResponseEntity.status(200).body("An OTP has been sent to reset your password");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
   @PostMapping(path = "/reset-password")
   public ResponseEntity<Object> resetPassword(@RequestParam String otp,
                                               @RequestParam String newPassword,
                                               @RequestParam String confirmPassword) {
       try {
           if (newPassword.equals(confirmPassword)) {
               passwordResetService.resetPassword(otp, newPassword);
               return ResponseEntity.status(200).body("Password successfully reset");
           } else {
               return ResponseEntity.status(400).body("Passwords do not match");
           }
       } catch (Exception e) {
           return ResponseEntity.status(400).body(e.getMessage());
       }
   }
}
