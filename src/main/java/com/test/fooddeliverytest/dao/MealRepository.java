package com.test.fooddeliverytest.dao;

import com.test.fooddeliverytest.model.Meal;
import com.test.fooddeliverytest.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Long> {

    List<Meal> findAllByNameStartsWith(String name);
    List<Meal> findAllByRestaurant(Restaurant restaurant);

}
