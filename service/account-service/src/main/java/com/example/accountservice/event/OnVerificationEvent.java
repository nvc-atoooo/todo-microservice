package com.example.accountservice.event;

import com.example.accountservice.domain.Account;
import com.example.accountservice.domain.VerificationType;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnVerificationEvent extends ApplicationEvent {

    private Account account;
    private VerificationType type;

    public OnVerificationEvent(Account account, VerificationType type) {
        super(account);

        this.account = account;
        this.type = type;
    }
}
