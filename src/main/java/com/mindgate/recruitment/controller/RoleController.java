package com.mindgate.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Role;
import com.mindgate.recruitment.exceptions.RoleNotFoundException;
import com.mindgate.recruitment.service.RoleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllRoles(){
		List<Role> roleList = roleService.getAllRoles();
		return ResponseEntity.status(200).body(roleList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getRoleById(@PathVariable("id") int id){
		try {
			Role role = roleService.getRoleById(id);
			return ResponseEntity.status(200).body(role);
		} catch (RoleNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
}
