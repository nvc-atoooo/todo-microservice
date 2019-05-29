package com.example.authservice.config;

import com.example.lib.api.controller.WebRestControllerAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebRestControllerAdvice.class})
public class ControllerConfig {
}
