package com.example.accountservice.service;

import com.example.accountservice.client.AuthServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthServiceClient authClient;

    @Override
    public ResponseEntity<?> getUser() {
        return authClient.getUser();
    }
    
}