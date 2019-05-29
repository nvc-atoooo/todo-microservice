package com.example.accountservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "verification_token")
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @SequenceGenerator(name = "verification_token_token_id_seq", sequenceName = "verification_token_token_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "verification_token_token_id_seq")
    @Column(name = "token_id", unique = true, nullable = false)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    @Column(name="expiry_date")
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private VerificationType type;

    public VerificationToken(String token, Account account, VerificationType type) {
        this(token, account, type, EXPIRATION);
    }

    public VerificationToken(String token, Account account, VerificationType type, int expiryDate) {
        this.token = token;
        this.account = account;
        this.expiryDate = calculateExpiryDate(expiryDate);
        this.type = type;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
