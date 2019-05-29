package com.example.accountservice.event.listener;

import com.example.accountservice.config.MailConfirmConfig;
import com.example.accountservice.domain.Account;
import com.example.accountservice.domain.VerificationToken;
import com.example.accountservice.domain.VerificationType;
import com.example.accountservice.event.OnVerificationEvent;
import com.example.accountservice.service.AccountService;
import com.example.accountservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VerificationListener implements ApplicationListener<OnVerificationEvent> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MailConfirmConfig mailConfirmConfig;

    @Override
    public void onApplicationEvent(OnVerificationEvent event) {
        this.verification(event);
    }

    private void verification(OnVerificationEvent event) {
        Account account = event.getAccount();
        VerificationType type = event.getType();

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = accountService.createVerificationToken(account, token, type);

        String recipientAddress = account.getEmail();
        String subject = mailConfirmConfig.getSubject(type);
        String message = mailConfirmConfig.getUiHostname() + mailConfirmConfig.getPath(type) + "?token=" + verificationToken.getToken();

        emailService.send(recipientAddress, subject, message);
    }
}
