package com.test.fooddeliverytest.dao;

import com.test.fooddeliverytest.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAllByCuisine(String cuisine);
    List<Restaurant> findAllByName(String name);
    List<Restaurant> findAllByNameStartsWith(String name);
}
