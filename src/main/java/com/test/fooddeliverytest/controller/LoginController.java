package com.test.fooddeliverytest.controller;


import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.UserLoginDTO;
import com.test.fooddeliverytest.security.JwtTokenProvider;
import com.test.fooddeliverytest.security.PasswordEncoderUtil;
import com.test.fooddeliverytest.security.SecurityConstants;
import com.test.fooddeliverytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO loginCredentials, BindingResult result) {

        if (result.hasErrors()){
            return Response.badValue("Missing data", "Binding Error").build();
        }

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginCredentials.getUsername(),
                    loginCredentials.getPassword()
            ));

            String token = tokenProvider.generateToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return Response.ok("Login successful").header(SecurityConstants.HEADER_STRING,
                    SecurityConstants.TOKEN_PREFIX + token).build();

        } catch (BadCredentialsException ex){
            return Response.unauthorized("Login failed: " + ex.getLocalizedMessage()).build();
        }
    }

    // FOR TEST PURPOSES ONLY
    @PostMapping("/login2")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid UserLoginDTO userInfo){

        if (userService.userExist(userInfo.getUsername())){
            return Response.badValue("User exists!", "Invalid username").build();
        }

        userInfo.setPassword(passwordEncoderUtil.encodePassword(userInfo.getPassword()));

        if (userService.createAdmin(userInfo.getUsername(), userInfo.getPassword()).isEmpty()){
            return Response.badValue("Can't create user", "User creation error").build();
        }

        return Response.ok("Admin has been added successfully").build();
    }

}
