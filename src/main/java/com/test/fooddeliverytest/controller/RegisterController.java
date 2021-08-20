package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.UserRegisterDTO;
import com.test.fooddeliverytest.model.User;
import com.test.fooddeliverytest.security.PasswordEncoderUtil;
import com.test.fooddeliverytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/public")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody @Valid UserRegisterDTO userInfo, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return Response.badValue("Invalid Data", "Binding error").build();
        }
        String username = userInfo.getUsername();
        if (userService.userExist(username)){
            return Response.badValue(String.format("Username %s already exists", username), "Username exists").build();
        }

        String password = passwordEncoderUtil.encodePassword(userInfo.getPassword());
        Optional<User> user = userService.createUserWithData(username, password, userInfo.getUserData());

        if (user.isEmpty()){
            return Response.internalError("Error on creating user").build();
        } else {
            return Response.ok("User created successfully, continue to login").build();
        }
    }

}
