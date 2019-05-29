package com.example.accountservice.service;

import com.example.accountservice.client.AuthServiceClient;
import com.example.accountservice.client.form.UserAuthForm;
import com.example.accountservice.controller.form.AccountForm;
import com.example.accountservice.domain.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.lib.exception.ResourceNotFoundException;
import com.example.lib.exception.ResourceViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AuthServiceClient authServiceClient;

    @Test(expected = ResourceNotFoundException.class)
    public void shouldFailWhenAccountDoesNotExist() throws Exception {
        Account account = new Account();
        account.setEmail("test@gmail.com");

        given(accountRepository.findByEmail(account.getEmail())).willReturn(null);
        accountService.getAccountByEmail(account.getEmail());
    }

    @Test
    public void shouldFindAccountByEmail() throws Exception {
        Account account = new Account();
        account.setEmail("test@gmail.com");

        given(accountRepository.findByEmail(account.getEmail())).willReturn(account);
        Account found = accountService.getAccountByEmail(account.getEmail());

        assertEquals(account, found);
    }

    @Test(expected = ResourceViolationException.class)
    public void shouldFailWhenExistAccount() throws Exception {
        AccountForm accountForm = new AccountForm();
        accountForm.setEmail("test@gmail.com");

        given(accountRepository.existsByEmail(accountForm.getEmail())).willReturn(true);
        accountService.createAccount(accountForm);
    }

    @Test
    public void shouldCreateAccountWithGivenAccountForm() throws Exception {
        AccountForm accountForm = new AccountForm();
        accountForm.setEmail("test@gmail.com");
        accountForm.setPassword("12345678");

        Account account = accountService.createAccount(accountForm);

        assertEquals(accountForm.getEmail(), account.getEmail());
        assertNull(account.getAddress());
        assertNull(account.getPhoneNumber());
        assertFalse(account.isEnabled());

        verify(accountRepository, times(1)).save(account);
        verify(authServiceClient, times(1)).saveUser(new UserAuthForm(accountForm));
    }
}
