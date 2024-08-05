package com.mindgate.recruitment.beans;

public class LoginReturnResult {
	public boolean result = true;
	public String role = "";
	public int empId;
	
	public LoginReturnResult(boolean result, String role,int empId) {
		super();
		this.result = result;
		this.role = role;
		this.empId=empId;
	}
	public int getEmpId() {
		return empId;
	}
	public boolean isResult() {
		return result;
	}
	public String getRole() {
		return role;
	}
}
