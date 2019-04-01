package com.example.accountservice.service;

import org.springframework.http.ResponseEntity;

public interface AccountService {
    
    public ResponseEntity<?>  getUser();
}