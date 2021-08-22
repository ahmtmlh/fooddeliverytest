package com.test.fooddeliverytest.dto;

import com.test.fooddeliverytest.dto.user.UserInfoDTO;
import com.test.fooddeliverytest.model.OrderData;

import java.util.ArrayList;
import java.util.List;

public class OrderDataLastOrdersDTO {

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

    public static OrderDataLastOrdersDTO fromOrderData(OrderData orderData) {
        OrderDataLastOrdersDTO orderDataInfoDTO = new OrderDataLastOrdersDTO();

        orderDataInfoDTO.setRestaurantInfo(RestaurantInfoDTO.fromRestaurant(orderData.getRestaurant()));
        List<MealInfoDTO> list = new ArrayList<>();
        orderData.getMealData().forEach(meal -> list.add(MealInfoDTO.fromMeal(meal)));
        orderDataInfoDTO.setMealInfoList(list);

        return orderDataInfoDTO;
    }

}
