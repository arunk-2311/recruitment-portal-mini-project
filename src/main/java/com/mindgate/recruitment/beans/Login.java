package com.mindgate.recruitment.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "login_view")
public class Login {
	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	private String password;
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "phone_no")
	private long phoneNo;
	private String email;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public String getEmail() {
		return email;
	}
}
