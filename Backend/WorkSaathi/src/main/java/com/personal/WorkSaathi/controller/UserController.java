package com.personal.WorkSaathi.controller;

import com.personal.WorkSaathi.dto.LoginDTO;
import com.personal.WorkSaathi.dto.UserDTO;
import com.personal.WorkSaathi.response.JwtResponse;
import com.personal.WorkSaathi.response.LoginResponse;
import com.personal.WorkSaathi.security.CustomerUserDetailService;
import com.personal.WorkSaathi.service.UserService;
import com.personal.WorkSaathi.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomerUserDetailService customeUserDetailService;

    private final JwtTokenUtil jwtTokenUtil;

    public UserController(AuthenticationManager authenticationManager,
                          CustomerUserDetailService customeUserDetailService,
                          JwtTokenUtil jwtTokenUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.customeUserDetailService = customeUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping( "/save")
    public String saveEmployee(@RequestBody UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createUser(@RequestBody LoginDTO authModel)
        throws Exception {

        getAuthenticate(authModel);
        UserDetails userDetails = customeUserDetailService.loadUserByUsername(authModel.getEmail());
        String token= jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);

    }

    private void getAuthenticate(LoginDTO authModel) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad credential");
        }
        catch (DisabledException e){
            throw new Exception("Disable exception");
        }

    }
}
