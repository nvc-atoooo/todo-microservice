package com.example.accountservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import feign.RequestInterceptor;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final ResourceServerProperties sso;

    @Autowired
    public ResourceServerConfig(ResourceServerProperties sso) {
        this.sso = sso;
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return template -> {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				Object details = auth.getDetails();
				if (details instanceof OAuth2AuthenticationDetails) {
					OAuth2AuthenticationDetails holder = (OAuth2AuthenticationDetails) details;
					template.header(HttpHeaders.AUTHORIZATION, holder.getTokenType() + " " + holder.getTokenValue());
				}
			}
		};
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        return new UserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/greeting").permitAll()
                .anyRequest().authenticated();
    }
}