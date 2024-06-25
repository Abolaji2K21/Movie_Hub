package com.mavericktube.MaverickHub.Security.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(){
            return null;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return  new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
