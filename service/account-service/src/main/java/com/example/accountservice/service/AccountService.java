package com.example.accountservice.service;

import com.example.accountservice.controller.form.AccountForm;
import com.example.accountservice.domain.Account;
import com.example.accountservice.domain.VerificationToken;
import com.example.accountservice.domain.VerificationType;
import com.example.lib.exception.ResourceViolationException;
import com.example.lib.exception.TokenViolationException;
import com.example.lib.exception.ResourceNotFoundException;

public interface AccountService {

    Account getAccountByEmail(String email) throws ResourceNotFoundException;

    Account createAccount(AccountForm accountForm) throws ResourceViolationException;

    void activateAccount(String token) throws TokenViolationException;

    VerificationToken createVerificationToken(Account account, String token, VerificationType type);

    VerificationToken getVerificationToken(String token) throws TokenViolationException;
}
