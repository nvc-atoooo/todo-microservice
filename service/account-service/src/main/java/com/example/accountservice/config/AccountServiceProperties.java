package com.example.accountservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("account")
public class AccountServiceProperties {

    private Verification verification = new Verification();

    private Path path = new Path();

    @Data
    public static class Verification {
        private int expiration;
    }

    @Data
    public static class Path {
        private String confirmRegistration;
        private String forgotPassword;
    }
}
