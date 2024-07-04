package com.personal.WorkSaathi.service;

import com.personal.WorkSaathi.dto.LoginDTO;
import com.personal.WorkSaathi.dto.UserDTO;
import com.personal.WorkSaathi.entity.User;
import com.personal.WorkSaathi.repository.UserRepository;
import com.personal.WorkSaathi.response.LoginResponse;
import java.util.Objects;
import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(UserDTO userDTO) {
        Optional<User> existingUser =
            userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return "User already present with this email";
        }
        User user = User.builder()
            .name(userDTO.getName())
            .password(passwordEncoder.encode(userDTO.getPassword()))
            .type(userDTO.getType())
            .email(userDTO.getEmail())
            .build();
        userRepository.save(user);
        return user.getName();
    }

    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (user.isPresent()) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.get().getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> existingUser = userRepository.findOneByEmailAndPassword(
                    loginDTO.getEmail()
                    , encodedPassword);
                if (existingUser.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        } else {
            return new LoginResponse("Email not exits", false);
        }
    }
}
