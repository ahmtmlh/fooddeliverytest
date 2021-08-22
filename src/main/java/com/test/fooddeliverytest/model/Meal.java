package com.test.fooddeliverytest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "meal_id")
    private Long id;
    @Column()
    private String imageUrl;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Ingredients", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name="ingredients")
    private List<String> ingredients;

    @ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID", referencedColumnName = "restaurant_id")
    private Restaurant restaurant;

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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() * this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Meal){
            Meal that = (Meal) obj;
            return that.getId().equals(this.getId());
        }

        return false;
    }
}
