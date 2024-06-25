package com.sejong.sejongbike.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class WebSecurityConfig {
    // securityFilterChain 대상에서 제외시킴

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/resources/**",
                "/static/**",
                "/css/**",
                "/js/**",
                "/assets/**",
                "/images/**",
                "/error/**",
                "/",
                "/index.html",
                "/favicon.*"
        );
    }
}
