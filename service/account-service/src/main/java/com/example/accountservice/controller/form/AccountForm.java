package com.example.accountservice.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class AccountForm {

    @Email
    @NotEmpty
    @NotNull(message = "Invalid email!")
    private String email;

    @NotEmpty
    @NotNull(message = "Password can not be null!")
    @Size(min = 8)
    private String password;

    private String phone_number;

    private String address;


}