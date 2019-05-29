package com.example.accountservice.config;

import com.example.accountservice.domain.VerificationType;
import com.example.lib.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailConfirmConfig {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private AccountServiceProperties accountProperties;

    private Map<VerificationType, String> mapUrl;

    private Map<VerificationType, String> mapSubject;

    @PostConstruct
    public void init() {
        mapUrl = new HashMap<>();
        mapUrl.put(VerificationType.REG, accountProperties.getPath().getConfirmRegistration());
        mapUrl.put(VerificationType.FORGOT_PASSWORD, accountProperties.getPath().getForgotPassword());

        mapSubject = new HashMap<>();
        mapSubject.put(VerificationType.REG, "User activation");
        mapSubject.put(VerificationType.FORGOT_PASSWORD, "Reset password");
    }

    public String getPath(VerificationType verificationType) {
        return mapUrl.get(verificationType);
    }

    public String getSubject(VerificationType verificationType) {
        return mapSubject.get(verificationType);
    }

    public String getUiHostname() {
        return appProperties.getUiHostname();
    }
}
