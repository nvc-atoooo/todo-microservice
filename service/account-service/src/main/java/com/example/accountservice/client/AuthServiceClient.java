package com.example.accountservice.client;

import com.example.accountservice.client.form.UserAuthForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/auth/users/registration")
    ResponseEntity<?> saveUser(UserAuthForm userAuthForm);

    @RequestMapping(method = RequestMethod.PUT, value = "/auth/users/registration-confirm")
    ResponseEntity<?> activateUser(String email);
}