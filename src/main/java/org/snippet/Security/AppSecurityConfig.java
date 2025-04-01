package org.snippet.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(2) // Lower priority for Spring MVC routes
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher(new AntPathRequestMatcher("/api/**")) // Explicitly use AntPathRequestMatcher
            .csrf().disable() // Disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/api/auth/login")).permitAll() // Allow login
                .requestMatchers(new AntPathRequestMatcher("/api/auth/register")).permitAll() // Allow register
                .requestMatchers(new AntPathRequestMatcher("/api/public/**")).permitAll() // Allow public resources
                .requestMatchers(new AntPathRequestMatcher("/api/error")).permitAll() // Allow error page
                .anyRequest().authenticated() // Require authentication for all other routes
            )
            .httpBasic(); // Enable basic authentication for simplicity

        return http.build();
    }
}