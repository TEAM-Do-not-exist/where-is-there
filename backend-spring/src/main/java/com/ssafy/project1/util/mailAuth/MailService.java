package com.ssafy.project1.util.mailAuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailService {

	Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	JavaMailSender javaMailSender;

	public boolean sendMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			javaMailSender.send(message);
			return true;
		} catch (MailException me) {
			logger.error("MailException", me);
			return false;
		}
	}
}
