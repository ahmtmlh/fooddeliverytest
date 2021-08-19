package com.test.fooddeliverytest.model;

import javax.persistence.*;

@Entity
public class User {

    public enum UserType{
        NORMAL,
        ADMIN
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType;
    @Column(unique = true)
    private String username;
    @Column
    private String password;

    public User(UserType userType, String username, String password) {
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
