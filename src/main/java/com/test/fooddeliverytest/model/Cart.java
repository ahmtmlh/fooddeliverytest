package com.test.fooddeliverytest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id", unique = true)
    private User user;

    @OneToMany(targetEntity = Meal.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_meal",
            joinColumns = @JoinColumn(name= "cart_id" ),
            inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private Set<Meal> meals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}