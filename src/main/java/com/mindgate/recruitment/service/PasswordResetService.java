package com.mindgate.recruitment.service;

public interface PasswordResetService {

	 public void initiatePasswordReset(String email) throws Exception;
	 public void resetPassword(String otp, String newPassword) throws Exception;
	 String generateOtp();
	 void sendOtpToEmail(String email, String otp);
	 
}
