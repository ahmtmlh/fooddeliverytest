package com.test.fooddeliverytest.dto.user;

import com.test.fooddeliverytest.model.UserData;

import javax.validation.constraints.NotNull;

public class UserRegisterDTO {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String address;
    private String phone;
    @NotNull
    private String email;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserData getUserData(){
        UserData userData = new UserData();
        userData.setAddress(getAddress());
        userData.setEmail(getEmail());
        userData.setName(getName());
        userData.setSurname(getSurname());
        userData.setPhone(getPhone());
        userData.setImageUrl(getImageUrl());
        return userData;
    }

}
