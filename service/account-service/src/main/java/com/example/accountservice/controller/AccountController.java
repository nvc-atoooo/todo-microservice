package com.example.accountservice.controller;

import com.example.accountservice.controller.form.AccountForm;
import com.example.accountservice.controller.view.AccountView;
import com.example.accountservice.domain.Account;
import com.example.accountservice.controller.form.ForgotPasswordForm;
import com.example.accountservice.domain.VerificationType;
import com.example.accountservice.event.OnVerificationEvent;
import com.example.accountservice.service.AccountService;
import com.example.lib.api.response.CreatedResponse;
import com.example.lib.exception.ResourceViolationException;
import com.example.lib.exception.TokenViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.lib.exception.ResourceNotFoundException;
import com.example.lib.api.response.NoContentResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/public/forgot-password")
    public NoContentResponse forgotPassword(@Valid @RequestBody ForgotPasswordForm form) throws ResourceNotFoundException {
        Account account = accountService.getAccountByEmail(form.getEmail());

        eventPublisher.publishEvent(new OnVerificationEvent(account, VerificationType.FORGOT_PASSWORD));

        return new NoContentResponse();
    }

    @PostMapping(value = "/public/registration")
    public ResponseEntity<?> register(@Valid @RequestBody AccountForm accountForm)
            throws ResourceViolationException {
        Account account = accountService.createAccount(accountForm);

        eventPublisher.publishEvent(new OnVerificationEvent(account, VerificationType.REG));

        AccountView accountView = new AccountView(account);

        return new CreatedResponse<>(accountView);
    }

    @GetMapping(value = "/public/registration-confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token)
            throws TokenViolationException {
        accountService.activateAccount(token);
        return new NoContentResponse();
    }
}