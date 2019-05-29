package com.example.accountservice.config;

import com.example.lib.api.controller.WebRestControllerAdvice;
import com.example.lib.config.AppProperties;
import com.example.lib.config.ClientCredentialsConfig;
import com.example.lib.config.ResourceServiceConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ClientCredentialsConfig.class, ResourceServiceConfig.class, WebRestControllerAdvice.class})
@EnableConfigurationProperties({AccountServiceProperties.class, AppProperties.class})
public class AccountServiceConfiguration {
}
