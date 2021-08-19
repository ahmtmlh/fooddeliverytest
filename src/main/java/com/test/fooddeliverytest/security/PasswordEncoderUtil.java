package com.test.fooddeliverytest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

}
