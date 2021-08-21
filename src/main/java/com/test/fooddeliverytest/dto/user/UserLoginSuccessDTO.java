package com.test.fooddeliverytest.dto.user;


public class UserLoginSuccessDTO {

    private String jwtToken;

    public UserLoginSuccessDTO() {
    }

    public UserLoginSuccessDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
