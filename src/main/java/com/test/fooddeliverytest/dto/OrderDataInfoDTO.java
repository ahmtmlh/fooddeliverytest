package com.test.fooddeliverytest.dto;

import com.test.fooddeliverytest.dto.user.UserInfoDTO;
import com.test.fooddeliverytest.model.OrderData;

import java.util.ArrayList;
import java.util.List;

public class OrderDataInfoDTO {

    private RestaurantInfoDTO restaurantInfo;
    private List<MealInfoDTO> mealInfoList;
    private UserInfoDTO userInfo;

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

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public static OrderDataInfoDTO fromOrderData(OrderData orderData){
        OrderDataInfoDTO orderDataInfoDTO = new OrderDataInfoDTO();

        orderDataInfoDTO.setUserInfo(UserInfoDTO.fromUser(orderData.getUser()));
        orderDataInfoDTO.setRestaurantInfo(RestaurantInfoDTO.fromRestaurant(orderData.getRestaurant()));

        List<MealInfoDTO> list = new ArrayList<>();
        orderData.getMealData().forEach(meal -> list.add(MealInfoDTO.fromMeal(meal)));
        orderDataInfoDTO.setMealInfoList(list);

        return orderDataInfoDTO;
    }

}

