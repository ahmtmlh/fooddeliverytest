package com.test.fooddeliverytest.service;

import com.test.fooddeliverytest.dao.OrderDataRepository;
import com.test.fooddeliverytest.model.OrderData;
import com.test.fooddeliverytest.model.Restaurant;
import com.test.fooddeliverytest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDataRepository orderDataRepository;

    public boolean doesUserHaveOrder(User user){
        return !orderDataRepository.findAllByUser(user).isEmpty();
    }

    public List<OrderData> getOrdersOfUser(User user){
        return orderDataRepository.findAllByUser(user);
    }

    public List<OrderData> getOrdersOfRestaurant(Restaurant restaurant){
        return orderDataRepository.findAllByRestaurant(restaurant);
    }

    public void createOrder(OrderData orderData){
        orderDataRepository.save(orderData);
    }

}
