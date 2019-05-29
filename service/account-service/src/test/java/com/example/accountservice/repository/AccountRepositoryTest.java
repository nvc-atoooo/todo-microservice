package com.example.accountservice.repository;

import com.example.accountservice.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldFindAccountByName() {
        Account account = new Account("test@gmail.com", "address", "012345678");
        accountRepository.save(account);

        Account found = accountRepository.findByEmail(account.getEmail());

        assertEquals(account.getEmail(), found.getEmail());
        assertEquals(account.getAddress(), found.getAddress());
        assertEquals(account.getPhoneNumber(), found.getPhoneNumber());
    }

    @Test
    public void shouldExistsAccountByEmail() {
        Account account = new Account("test@gmail.com", "address", "012345678");
        accountRepository.save(account);

        assertTrue(accountRepository.existsByEmail(account.getEmail()));
        assertFalse(accountRepository.existsByEmail("test1@gmail.com"));
    }
}
