package com.example.accountservice.service;

import org.springframework.mail.MailException;

public interface EmailService {
    void send(String receiver, String subject, String message) throws MailException;
}
