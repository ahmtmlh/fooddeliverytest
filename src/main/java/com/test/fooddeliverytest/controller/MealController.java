package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.annotation.AuthorizeAdmin;
import com.test.fooddeliverytest.annotation.AuthorizeUser;
import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.MealInfoDTO;
import com.test.fooddeliverytest.model.Meal;
import com.test.fooddeliverytest.model.Restaurant;
import com.test.fooddeliverytest.service.MealService;
import com.test.fooddeliverytest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rest")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private RestaurantService restaurantService;

    @AuthorizeUser
    @GetMapping("/restaurant/meals/{restaurant_id}")
    public ResponseEntity<Response> getRestaurantMeals(@PathVariable(name="restaurant_id") @Valid Long restaurantId){
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant.isEmpty()){
            return Response.notFound(String.format("No restaurant with id %d was found!", restaurantId)).build();
        }

        Set<Meal> meals = restaurant.get().getMeals();
        if (meals.isEmpty()){
            return Response.notFound(String.format("No meals found for restaurant: %d", restaurantId)).build();
        }
        List<MealInfoDTO> mealInformation = new ArrayList<>();
        meals.forEach(meal -> mealInformation.add(MealInfoDTO.fromMeal(meal)));

        return Response.ok("Success").body(mealInformation).build();
    }

    @AuthorizeUser
    @GetMapping("/meals/{meal_id}")
    public ResponseEntity<Response> getMealById(@PathVariable(name="meal_id") @Valid Long mealId){
        Optional<Meal> meal = mealService.getMealById(mealId);

        if (meal.isEmpty()){
            return Response.notFound(String.format("No meal with id %d was found!", mealId)).build();
        }

        return Response.ok("Success").body(MealInfoDTO.fromMeal(meal.get())).build();
    }

    @AuthorizeAdmin
    @PutMapping("/meals/add/{restaurant_id}")
    public ResponseEntity<Response> addMealToRestaurant(@PathVariable(name="restaurant_id") @Valid Long restaurantId,
                                                        @RequestBody @Valid MealInfoDTO mealInfo,
                                                        BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return Response.badValue("Invalid Data", "Binding error").build();
        }

        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant.isEmpty()){
            return Response.notFound(String.format("Restaurant with ID: %d not found!", restaurantId)).build();
        }

        Meal meal = new Meal();
        meal.setImageUrl(mealInfo.getImageUrl());
        meal.setDescription(mealInfo.getDescription());
        meal.setPrice(mealInfo.getPrice());
        meal.setName(mealInfo.getName());
        meal.setIngredients(mealInfo.getIngredients());
        meal.setRestaurant(restaurant.get());

        Meal saved = mealService.addMeal(meal);

        return Response.ok("Successfully added meal.ID: " + saved.getId()).build();

    }


}
