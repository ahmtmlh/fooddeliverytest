package com.test.fooddeliverytest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cuisine;
    @Column(nullable = false)
    private String information;
    @Column()
    private String imageUrl;
    @Column(nullable = false)
    private String minDeliveryTime;
    @Column(nullable = false)
    private String minDeliveryFee;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Meal.class, mappedBy = "restaurant")
    private Set<Meal> meals;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}
