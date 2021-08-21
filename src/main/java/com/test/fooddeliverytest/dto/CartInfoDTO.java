package com.test.fooddeliverytest.dto;

import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class CartInfoDTO {

    private RestaurantInfoDTO restaurantInfo;
    private List<MealInfoDTO> mealInfoList;

    public RestaurantInfoDTO getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfoDTO restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public List<MealInfoDTO> getMealInfoList() {
        return mealInfoList;
    }

    public void setMealInfoList(List<MealInfoDTO> mealInfoList) {
        this.mealInfoList = mealInfoList;
    }

    public static CartInfoDTO fromCart(Cart cart){
        CartInfoDTO cartInfoDTO = new CartInfoDTO();

        List<MealInfoDTO> list = new ArrayList<>();
        List<Meal> meals = cart.getMeals();

        RestaurantInfoDTO restaurantInfoDTO = RestaurantInfoDTO.fromRestaurant(meals.get(0).getRestaurant());
        meals.forEach(meal -> list.add(MealInfoDTO.fromMeal(meal)));

        cartInfoDTO.setMealInfoList(list);
        cartInfoDTO.setRestaurantInfo(restaurantInfoDTO);

        return cartInfoDTO;
    }

}
