package com.example.authservice.service;

import com.example.authservice.controller.form.UserForm;
import com.example.authservice.domain.User;
import com.example.lib.exception.ResourceViolationException;

public interface UserService {

    User createUser(UserForm userForm) throws ResourceViolationException;

    void activateUser(String email) throws ResourceViolationException;

    User getByUsername(String username) throws ResourceViolationException;
}
