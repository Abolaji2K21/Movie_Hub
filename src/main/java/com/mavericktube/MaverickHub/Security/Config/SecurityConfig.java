package com.mavericktube.MaverickHub.Security.Config;


import com.mavericktube.MaverickHub.Security.filters.CustomUsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomUsernamePasswordAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        authenticationFilter.setFilterProcessesUrl("/api/v1/auth");
            return http.csrf(c->c.disable())
                        .cors(c->c.disable())
                    .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                    .authorizeHttpRequests(c->c.anyRequest().permitAll()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return  new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
