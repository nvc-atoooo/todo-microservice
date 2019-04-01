package com.example.accountservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/auth/users/current")
    ResponseEntity<?> getUser();
}