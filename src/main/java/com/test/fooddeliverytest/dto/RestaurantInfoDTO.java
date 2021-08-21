package com.test.fooddeliverytest.dto;

import com.test.fooddeliverytest.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RestaurantInfoDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String cuisine;
    @NotNull
    private String information;
    @NotNull
    private String minDeliveryTime;
    @NotNull
    private String minDeliveryFee;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getMinDeliveryTime() {
        return minDeliveryTime;
    }

    public void setMinDeliveryTime(String minDeliveryTime) {
        this.minDeliveryTime = minDeliveryTime;
    }

    public String getMinDeliveryFee() {
        return minDeliveryFee;
    }

    public void setMinDeliveryFee(String minDeliveryFee) {
        this.minDeliveryFee = minDeliveryFee;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static RestaurantInfoDTO fromRestaurant(Restaurant restaurant){
        RestaurantInfoDTO restaurantInfoDTO = new RestaurantInfoDTO();

        restaurantInfoDTO.setCuisine(restaurant.getCuisine());
        restaurantInfoDTO.setInformation(restaurant.getInformation());
        restaurantInfoDTO.setId(restaurant.getId());
        restaurantInfoDTO.setName(restaurant.getName());
        restaurantInfoDTO.setMinDeliveryFee(restaurant.getMinDeliveryFee());
        restaurantInfoDTO.setMinDeliveryTime(restaurant.getMinDeliveryTime());
        restaurantInfoDTO.setImageUrl(restaurant.getImageUrl());

        return restaurantInfoDTO;
    }
}
