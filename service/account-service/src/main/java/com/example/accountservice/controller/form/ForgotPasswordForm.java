package com.example.accountservice.controller.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ForgotPasswordForm {

    @Email
    @NotEmpty
    private String email;
}
