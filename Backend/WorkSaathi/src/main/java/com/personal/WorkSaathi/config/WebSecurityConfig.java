package com.personal.WorkSaathi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.personal.WorkSaathi.repository.UserRepository;
import com.personal.WorkSaathi.security.CustomerUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserRepository userRepository;

    private final CustomerUserDetailService customerUserDetailService;


    public WebSecurityConfig(
        UserRepository userRepository, CustomerUserDetailService customerUserDetailService) {
        this.userRepository = userRepository;
        this.customerUserDetailService = new CustomerUserDetailService(userRepository);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
            .authorizeHttpRequests(authorize -> authorize
                                       .requestMatchers("/login", "/save").permitAll() // Allow access to /login and /save
                                       .anyRequest().authenticated() // All other requests need to be authenticated
                                  )
            .httpBasic(withDefaults());

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        PasswordEncoder encoder = passwordEncoder();
//
//        UserDetails user1 = User.withUsername("Anshu")
//            .password(encoder.encode("12345"))
//            .roles("USER")
//            .build();
//        UserDetails user2 = User.withUsername("Nishant")
//            .password(encoder.encode("123456"))
//            .roles("USER")
//            .build();
//        userDetailsManager.createUser(user1);
//        userDetailsManager.createUser(user2);
//        return userDetailsManager;
//    }

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
