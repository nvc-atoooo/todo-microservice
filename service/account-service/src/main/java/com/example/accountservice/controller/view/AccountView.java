package com.example.accountservice.controller.view;

import com.example.accountservice.domain.Account;
import lombok.Value;

@Value
public class AccountView {

    private int id;

    private boolean enabled;

    private String email;

    private String phone_number;

    private String address;

    public AccountView(Account account) {
        this(account.getId(), account.isEnabled(), account.getEmail(), account.getPhoneNumber(), account.getAddress());
    }

    public AccountView(int id, boolean enabled, String email, String phone_number, String address) {
        this.id = id;
        this.enabled = enabled;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
    }
}
