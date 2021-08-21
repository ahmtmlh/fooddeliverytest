package com.test.fooddeliverytest.controller;


import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.user.UserLoginDTO;
import com.test.fooddeliverytest.dto.user.UserLoginSuccessDTO;
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
            UserLoginSuccessDTO loginSuccess = new UserLoginSuccessDTO(SecurityConstants.TOKEN_PREFIX + token);

            return Response.ok("Login successful").body(loginSuccess).build();

        } catch (BadCredentialsException ex){
            return Response.unauthorized("Login failed: " + ex.getLocalizedMessage()).build();
        }
    }

}
