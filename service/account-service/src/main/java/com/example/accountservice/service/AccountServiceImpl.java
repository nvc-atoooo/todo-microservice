package com.example.accountservice.service;

import com.example.accountservice.client.AuthServiceClient;

import com.example.accountservice.client.form.UserAuthForm;
import com.example.accountservice.config.AccountServiceProperties;
import com.example.accountservice.controller.form.AccountForm;
import com.example.accountservice.domain.Account;
import com.example.accountservice.domain.VerificationToken;
import com.example.accountservice.domain.VerificationType;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.repository.VerificationTokenRepository;
import com.example.lib.exception.ResourceViolationException;
import com.example.lib.exception.TokenViolationException;
import com.example.lib.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthServiceClient authClient;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private AccountServiceProperties accountProperties;

    @Override
    public Account getAccountByEmail(String email) throws ResourceNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new ResourceNotFoundException("Email does not exist");
        }

        return account;
    }

    @Override
    @Transactional
    public Account createAccount(AccountForm accountForm) throws ResourceViolationException {
        if(accountRepository.existsByEmail(accountForm.getEmail())) {
            throw new ResourceViolationException("This email has been registered");
        }

        Account account = new Account(accountForm);
        accountRepository.save(account);

        UserAuthForm userAuthForm = new UserAuthForm(accountForm);
        authClient.saveUser(userAuthForm);

        return account;
    }

    @Override
    @Transactional
    public void activateAccount(String token) throws TokenViolationException {
        VerificationToken verificationToken = getVerificationToken(token);

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new TokenViolationException("Token expired");
        }

        Account account = verificationToken.getAccount();
        account.setEnabled(true);
        accountRepository.save(account);

        authClient.activateUser(account.getEmail());
    }

    @Override
    @Transactional
    public VerificationToken createVerificationToken(Account account, String token, VerificationType type) {
        int expiration = accountProperties.getVerification().getExpiration();
        VerificationToken verificationToken = new VerificationToken(token, account, type, expiration);
        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    @Override
    public VerificationToken getVerificationToken(String token) throws TokenViolationException {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            throw new TokenViolationException("Token not exist");
        }

        return verificationToken;
    }
}