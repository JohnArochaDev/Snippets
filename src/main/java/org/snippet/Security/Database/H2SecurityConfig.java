package org.snippet.Security.Database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(1) // Higher priority for H2 console
public class H2SecurityConfig {

    @Bean
    public SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher(new AntPathRequestMatcher("/h2/**")) // Explicitly use AntPathRequestMatcher
            .csrf().disable() // Disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all requests to H2 console
            .headers(headers -> headers.frameOptions().disable()); // Allow H2 console to render frames

        return http.build();
    }
}