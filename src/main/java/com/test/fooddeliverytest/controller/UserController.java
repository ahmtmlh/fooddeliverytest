package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.UserLoginDTO;
import com.test.fooddeliverytest.security.PasswordEncoderUtil;
import com.test.fooddeliverytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/normal")
    public ResponseEntity<?> registerNormalUser(@RequestBody @Valid UserLoginDTO userInfo, BindingResult result){

        if (result.hasErrors()){
            return Response.badValue("Invalid data received", "Binding error").build();
        }

        if (userService.userExist(userInfo.getUsername())) {
            return Response.badValue("User exists!", "Invalid username").build();
        }

        userInfo.setPassword(passwordEncoderUtil.encodePassword(userInfo.getPassword()));

        if (userService.createUser(userInfo.getUsername(), userInfo.getPassword()).isEmpty()) {
            return Response.badValue("Can't create user", "User creation error").build();
        }

        return Response.ok("User has been added successfully").build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/admin")
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
