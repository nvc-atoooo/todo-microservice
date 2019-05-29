package com.example.authservice.controller;

import java.security.Principal;

import com.example.authservice.controller.view.UserView;
import com.example.authservice.domain.User;
import com.example.authservice.service.UserService;
import com.example.lib.api.response.CreatedResponse;
import com.example.lib.exception.ResourceViolationException;
import com.example.lib.api.response.NoContentResponse;
import com.example.authservice.controller.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/current")
    public ResponseEntity<?> getUser(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping(value = "/registration")
    public ResponseEntity<?> register(@RequestBody @Valid UserForm userForm) throws ResourceViolationException {
        User user = userService.createUser(userForm);
        UserView userView = new UserView(user);

        return new CreatedResponse<>(userView);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PutMapping(value = "/registration-confirm")
    public NoContentResponse confirmRegistration(@RequestBody String username) throws ResourceViolationException {
        userService.activateUser(username);
        return new NoContentResponse();
    }
}