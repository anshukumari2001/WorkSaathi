package com.personal.WorkSaathi.controller;

import com.personal.WorkSaathi.dto.LoginDTO;
import com.personal.WorkSaathi.dto.UserDTO;
import com.personal.WorkSaathi.response.LoginResponse;
import com.personal.WorkSaathi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
