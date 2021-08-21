package com.test.fooddeliverytest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "USER_ID", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Restaurant.class)
    @JoinColumn(nullable = false, name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Meal.class)
    @JoinColumn(name = "meal_id", nullable = false)
    private Set<Meal> mealData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Meal> getMealData() {
        return mealData;
    }

    public void setMealData(Set<Meal> mealData) {
        this.mealData = mealData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
