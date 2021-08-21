package com.test.fooddeliverytest.dto.user;

import javax.validation.constraints.NotNull;

public class UserLoginDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;

    public UserLoginDTO(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
