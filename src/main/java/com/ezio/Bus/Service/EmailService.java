package com.ezio.Bus.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
	
	String sendMail(MultipartFile[] files, String to, String[] cc, String subject, String body);
	
	String sendMail(String to, String subject, String body);

	void sendSimpleEmail(String email, String subject, String body);

	void sendOtp(String email);

	boolean verifyOtp(String email, String otp);

	void changePassword(String email, String newPassword, String confirmPassword);
	
    public String sendEmail(List<String> recipients, String subject, String body);

	

}
