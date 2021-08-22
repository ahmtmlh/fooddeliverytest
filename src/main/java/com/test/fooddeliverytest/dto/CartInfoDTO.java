package com.test.fooddeliverytest.dto;

import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class CartInfoDTO {

    private RestaurantInfoDTO restaurantInfo;
    private List<CartMealInfoDTO> mealInfoList;

    public RestaurantInfoDTO getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfoDTO restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public List<CartMealInfoDTO> getMealInfoList() {
        return mealInfoList;
    }

    public void setMealInfoList(List<CartMealInfoDTO> mealInfoList) {
        this.mealInfoList = mealInfoList;
    }

    public static CartInfoDTO fromCart(Cart cart){
        CartInfoDTO cartInfoDTO = new CartInfoDTO();

        List<Meal> meals = cart.getMeals();
        List<CartMealInfoDTO> list = new ArrayList<>();
        RestaurantInfoDTO restaurantInfoDTO = RestaurantInfoDTO.fromRestaurant(meals.get(0).getRestaurant());

        for (Meal meal: meals) {
            CartMealInfoDTO cartMealInfo = new CartMealInfoDTO();
            cartMealInfo.setMealInfo(MealInfoDTO.fromMeal(meal));

            int index = list.indexOf(cartMealInfo);

            if (index == -1){
                list.add(cartMealInfo);
            } else {
                list.get(index).incrementCount();
            }
        }

        cartInfoDTO.setMealInfoList(list);
        cartInfoDTO.setRestaurantInfo(restaurantInfoDTO);

        return cartInfoDTO;
    }

}
