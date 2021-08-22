package com.test.fooddeliverytest.dto.user;

import com.test.fooddeliverytest.dto.OrderDataInfoDTO;
import com.test.fooddeliverytest.dto.OrderDataLastOrdersDTO;
import com.test.fooddeliverytest.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDTO {

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String address;
    private String phone;

    private List<OrderDataLastOrdersDTO> lastOrders;

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

    public List<OrderDataLastOrdersDTO> getLastOrders() {
        return lastOrders;
    }

    public void setLastOrders(List<OrderDataLastOrdersDTO> lastOrders) {
        this.lastOrders = lastOrders;
    }

    public static UserInfoDTO fromUser(User user){
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setAddress(user.getUserData().getAddress());
        userInfoDTO.setEmail(user.getUserData().getEmail());
        userInfoDTO.setName(user.getUserData().getName());
        userInfoDTO.setSurname(user.getUserData().getSurname());
        userInfoDTO.setPhone(user.getUserData().getPhone());

        List<OrderDataLastOrdersDTO> list = new ArrayList<>();
        user.getUserData().getLastOrders().forEach(orderData -> list.add(OrderDataLastOrdersDTO.fromOrderData(orderData)));
        userInfoDTO.setLastOrders(list);

        userInfoDTO.setUsername(user.getUsername());

        return userInfoDTO;
    }

}
