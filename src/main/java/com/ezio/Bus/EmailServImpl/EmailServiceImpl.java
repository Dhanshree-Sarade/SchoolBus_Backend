package com.ezio.Bus.EmailServImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezio.Bus.Repository.StudentRepository;
import com.ezio.Bus.Service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	 // Store OTPs temporarily
    private Map<String, String> otpStorage = new HashMap<>();
    
    public String sendMail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }

	@Override
	public String sendMail(MultipartFile[] files, String to, String[] cc, String subject, String body) {
		// TODO Auto-generated method stub
		try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            if (cc != null && cc.length > 0) {
                helper.setCc(cc);
            }
            helper.setSubject(subject);
            helper.setText(body, true);

            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    helper.addAttachment(file.getOriginalFilename(), file);
                }
            }

            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
	}

	@Override
	public void sendSimpleEmail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    

	@Override
	public void sendOtp(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changePassword(String email, String newPassword, String confirmPassword) {
		// TODO Auto-generated method stub
		
	}
	
	 // New method to send emails to multiple recipients
    public String sendEmail(List<String> recipients, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Convert List<String> to an array
            String[] recipientArray = recipients.toArray(new String[0]);

            helper.setTo(recipientArray); // Send to all recipients
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(mimeMessage);
            return "Mail sent successfully to " + recipients.size() + " students";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }

	
}
