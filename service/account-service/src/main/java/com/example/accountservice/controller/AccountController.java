package com.example.accountservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.accountservice.domain.Greeting;
import com.example.accountservice.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private AccountService authService;

    @RequestMapping("/greeting")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value = "/current", method = RequestMethod.GET)
	public ResponseEntity<?>  getUser() {
		return authService.getUser();
	}
}