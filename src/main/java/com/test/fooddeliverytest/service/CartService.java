package com.test.fooddeliverytest.service;


import com.test.fooddeliverytest.dao.CartRepository;
import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Optional<Cart> getCartOfUserFromDatabase(User user){
        return cartRepository.findByUser(user);
    }

    public void updateCart(Cart cart){
        cartRepository.save(cart);
    }

    public Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

}
