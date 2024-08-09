package com.mindgate.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mindgate.recruitment.beans.Login;
import com.mindgate.recruitment.exceptions.LoginNotFoundException;
import com.mindgate.recruitment.repository.LoginRepository;

import java.util.Optional;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Temporary storage for OTP
    private final Map<String, String> otpStore = new HashMap<>();
    @Override
    public void initiatePasswordReset(String email) throws Exception {
    	
        Optional<Login> loginOpt = loginRepository.findByEmail(email);
        if (loginOpt.isPresent()) {
        	System.out.println("The guy is" + loginOpt);
        	System.out.println("OTP being generated");
            String otp = generateOtp();
            System.out.println("The otp is: "+otp);
            otpStore.put(email, otp);
            sendOtpToEmail(email, otp);
        } else {
            throw new LoginNotFoundException("Email not found");
        }
    }

    @Override
    public void resetPassword(String otp, String newPassword) throws Exception {
        Optional<String> emailOpt = otpStore.entrySet()
                                            .stream()
                                            .filter(entry -> entry.getValue().equals(otp))
                                            .map(Map.Entry::getKey)
                                            .findFirst();
        System.out.println("The emailopt is "+ emailOpt);
        if (emailOpt.isPresent()) {
            String email = emailOpt.get();
            Login login = loginRepository.findByEmail(email)
                                         .orElseThrow(() -> new LoginNotFoundException("Email not found"));
            login.setPassword(newPassword);
            loginRepository.save(login);
            otpStore.remove(email);
        } else {
            throw new Exception("Invalid OTP");
        }
    }
    @Override
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
    @Override
    public void sendOtpToEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        String from = "anupamsingh1896@gmail.com";
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for password reset is: " + otp);
        mailSender.send(message);
        System.out.println("Sent");
    }
}
