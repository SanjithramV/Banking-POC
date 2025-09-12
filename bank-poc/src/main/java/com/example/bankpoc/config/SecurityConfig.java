package com.example.bankpoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpass")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for H2 console
                .headers(headers -> headers.frameOptions().disable()) // allow frames for H2 console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // allow H2 console
                        .requestMatchers("/admin").hasRole("ADMIN") // admin endpoint
                        .anyRequest().permitAll() // everything else
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
