package com.test.fooddeliverytest.service;

import com.test.fooddeliverytest.dao.RestaurantRepository;
import com.test.fooddeliverytest.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> list = new ArrayList<>();
        restaurantRepository.findAll().forEach(list::add);
        return list;
    }

    public Optional<Restaurant> getRestaurantById(Long id){
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine){
        return restaurantRepository.findAllByCuisine(cuisine);
    }

    public List<Restaurant> getRestaurantsByName(String restaurantName){
        return restaurantRepository.findAllByName(restaurantName);
    }

    public List<Restaurant> getRestaurantsByNameStartingWith(String restaurantName){
        return restaurantRepository.findAllByNameStartsWith(restaurantName);
    }
}
