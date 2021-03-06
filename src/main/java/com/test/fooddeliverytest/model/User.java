package com.test.fooddeliverytest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    public enum UserType{
        NORMAL,
        ADMIN
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType;
    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = UserData.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userdata_id", unique = true, nullable = false)
    private UserData userData;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Cart.class, mappedBy = "user")
    private Cart cart;

    @OneToMany(targetEntity = OrderData.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderData> lastOrders;


    public User(UserType userType, String username, String password) {
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public User() { }

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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<OrderData> getLastOrders() {
        return lastOrders;
    }

    public void setLastOrders(List<OrderData> lastOrders) {
        this.lastOrders = lastOrders;
    }
}
