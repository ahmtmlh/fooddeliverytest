package com.test.fooddeliverytest.dto;

import com.test.fooddeliverytest.model.Meal;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MealInfoDTO {

    @NotNull
    private Long id;
    private String imageUrl;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Long price;
    @NotNull
    private List<String> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public static MealInfoDTO fromMeal(Meal meal){
        MealInfoDTO mealInfoDTO = new MealInfoDTO();

        mealInfoDTO.setDescription(meal.getDescription());
        mealInfoDTO.setId(meal.getId());
        mealInfoDTO.setName(meal.getName());
        mealInfoDTO.setImageUrl(meal.getImageUrl());
        mealInfoDTO.setPrice(meal.getPrice());
        mealInfoDTO.setIngredients(meal.getIngredients());

        return mealInfoDTO;
    }

}
