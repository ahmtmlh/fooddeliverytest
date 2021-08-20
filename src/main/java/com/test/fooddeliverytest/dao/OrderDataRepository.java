package com.test.fooddeliverytest.dao;

import com.test.fooddeliverytest.model.OrderData;
import com.test.fooddeliverytest.model.Restaurant;
import com.test.fooddeliverytest.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDataRepository extends CrudRepository<OrderData, Long> {
    List<OrderData> findAllByRestaurant(Restaurant restaurant);
    List<OrderData> findAllByUser(User user);
}
