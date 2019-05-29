package com.example.accountservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(String receiver, String subject, String message) throws MailException {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(receiver);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
