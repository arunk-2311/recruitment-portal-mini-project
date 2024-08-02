package com.mindgate.recruitment.beans;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	@Column(name="role")
	private String empRole;
	public int getRoleId() {
		return roleId;
	}
	public String getEmpRole() {
		return empRole;
	}
}
