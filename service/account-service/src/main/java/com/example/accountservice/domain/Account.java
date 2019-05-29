package com.example.accountservice.domain;

import com.example.accountservice.controller.form.AccountForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @SequenceGenerator(name = "account_account_id_seq", sequenceName = "account_account_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "account_account_id_seq")
    @Column(name = "account_id", unique = true, nullable = false)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    private boolean enabled;

    public Account(AccountForm accountForm) {
        this(accountForm.getEmail(), accountForm.getAddress(), accountForm.getPhone_number());
    }

    public Account(String email, String address, String phoneNumber) {
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}