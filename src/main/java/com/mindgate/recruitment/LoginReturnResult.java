package com.mindgate.recruitment;

public class LoginReturnResult {
	public boolean result = true;
	public String role = "";
	
	public LoginReturnResult(boolean result, String role) {
		super();
		this.result = result;
		this.role = role;
	}
	public boolean isResult() {
		return result;
	}
	public String getRole() {
		return role;
	}
}
