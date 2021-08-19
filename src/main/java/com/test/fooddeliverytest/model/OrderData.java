package com.test.fooddeliverytest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Restaurant.class)
    @JoinColumn(nullable = false, name = "restaurant_id")
    private Restaurant restaurantData;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Meal.class)
    @JoinColumn(name = "meal_id")
    private Set<Meal> mealData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurantData() {
        return restaurantData;
    }

    public void setRestaurantData(Restaurant restaurantData) {
        this.restaurantData = restaurantData;
    }

    public Set<Meal> getMealData() {
        return mealData;
    }

    public void setMealData(Set<Meal> mealData) {
        this.mealData = mealData;
    }
}
