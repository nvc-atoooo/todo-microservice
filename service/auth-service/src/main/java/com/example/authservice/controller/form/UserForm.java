package com.example.authservice.controller.form;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class UserForm {

    @NotEmpty
    @NotNull(message = "Username can not be null!")
    @Size(min = 8)
    private String username;

    @NotEmpty
    @NotNull(message = "Password can not be null!")
    @Size(min = 8)
    private String password;



}