package com.example.accountservice.client.form;

import com.example.accountservice.controller.form.AccountForm;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAuthForm {
    private String username;
    private String password;

    public UserAuthForm(AccountForm accountForm) {
        this(accountForm.getEmail(), accountForm.getPassword());
    }

    public UserAuthForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
