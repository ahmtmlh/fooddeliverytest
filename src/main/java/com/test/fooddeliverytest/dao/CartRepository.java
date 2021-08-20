package com.test.fooddeliverytest.dao;

import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUser(User user);
}
