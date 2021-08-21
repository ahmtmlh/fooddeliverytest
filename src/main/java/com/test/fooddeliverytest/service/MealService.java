package com.test.fooddeliverytest.service;


import com.test.fooddeliverytest.dao.MealRepository;
import com.test.fooddeliverytest.model.Meal;
import com.test.fooddeliverytest.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getMealsStartsWith(String name){
        return mealRepository.findAllByNameStartsWith(name);
    }

    public List<Meal> getMealsOfRestaurant(Restaurant restaurant){
        return mealRepository.findAllByRestaurant(restaurant);
    }

    public List<Meal> getMealsOfRestaurantBetweenPrices(Restaurant restaurant, Long priceLow, Long priceHigh){
        List<Meal> list = new ArrayList<>();
        List<Meal> mealsOfRestaurant = getMealsOfRestaurant(restaurant);
        mealsOfRestaurant.forEach(meal -> {
            if (meal.getPrice() <= priceHigh && meal.getPrice() >= priceLow)
                list.add(meal);
        });

        return list;
    }

    public Optional<Meal> getMealById(Long id){
        return mealRepository.findById(id);
    }

}
