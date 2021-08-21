package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.user.UserInfoDTO;
import com.test.fooddeliverytest.dto.user.UserLoginDTO;
import com.test.fooddeliverytest.model.User;
import com.test.fooddeliverytest.security.PasswordEncoderUtil;
import com.test.fooddeliverytest.security.UserPrincipal;
import com.test.fooddeliverytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add/normal")
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
    @PostMapping("/add/admin")
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

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/user")
    public ResponseEntity<Response> getUserInfo(@AuthenticationPrincipal UserPrincipal principal){

        if (principal == null || principal.getUsername().isBlank()) {
            return Response.unauthorized("Invalid user context!").build();
        }

        Optional<User> user = userService.userDetails(principal.getUsername());

        if (user.isEmpty()) {
            return Response.notFound("User not found!").build();
        }

        return Response.ok("Success").body(UserInfoDTO.fromUser(user.get())).build();
    }

    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/user/update")
    public ResponseEntity<Response> updateUserInfo(@RequestBody @Valid UserInfoDTO userInfo,
                                                   BindingResult bindingResult,
                                                   @AuthenticationPrincipal UserPrincipal principal) {

        if (principal == null || principal.getUsername().isBlank()) {
            return Response.unauthorized("Invalid user context!").build();
        }

        if (bindingResult.hasErrors()){
            return Response.badValue("Invalid data LOL", "Binding result has errors").build();
        }

        Optional<User> optionalUser = userService.userDetails(principal.getUsername());

        if (optionalUser.isEmpty()){
            return Response.notFound("User not found!").build();
        }

        User user = optionalUser.get();

        user.setUsername(userInfo.getUsername());
        user.setPassword(passwordEncoderUtil.encodePassword(userInfo.getPassword()));

        if (StringUtils.hasText(userInfo.getAddress()))
            user.getUserData().setAddress(userInfo.getAddress());
        if (StringUtils.hasText(userInfo.getEmail()))
            user.getUserData().setAddress(userInfo.getEmail());
        if (StringUtils.hasText(userInfo.getPhone()))
            user.getUserData().setAddress(userInfo.getPhone());
        if (StringUtils.hasText(userInfo.getName()))
            user.getUserData().setAddress(userInfo.getName());
        if (StringUtils.hasText(userInfo.getSurname()))
            user.getUserData().setAddress(userInfo.getSurname());

        userService.updateUser(user);

        return Response.ok("Update successful").build();
    }

}
