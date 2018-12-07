package com.example.authservice.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}