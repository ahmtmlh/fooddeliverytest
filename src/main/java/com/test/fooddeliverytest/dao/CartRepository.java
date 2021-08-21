package com.test.fooddeliverytest.dao;

import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
