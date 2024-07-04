package com.personal.WorkSaathi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.personal.WorkSaathi.repository.UserRepository;
import com.personal.WorkSaathi.security.CustomerUserDetailService;
import com.personal.WorkSaathi.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserRepository userRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                                       .requestMatchers("/login", "/save").permitAll()
                                       .anyRequest().authenticated()
                                  )
            .sessionManagement(session -> session
                                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                              )
            .addFilterBefore(authenticationJwtTokenFilter(),
                             UsernamePasswordAuthenticationFilter.class)
            .httpBasic(withDefaults());

        AuthenticationManagerBuilder auth = http.getSharedObject(
            AuthenticationManagerBuilder.class);
        auth.userDetailsService(new CustomerUserDetailService(userRepository)).passwordEncoder(
            passwordEncoder());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
