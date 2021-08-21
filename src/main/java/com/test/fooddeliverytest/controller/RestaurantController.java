package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.RestaurantInfoDTO;
import com.test.fooddeliverytest.model.Restaurant;
import com.test.fooddeliverytest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/restaurants")
    public ResponseEntity<Response> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        if (restaurants.isEmpty()){
            return Response.notFound("No restaurants were found!").build();
        }

        List<RestaurantInfoDTO> infos = new ArrayList<>();
        restaurants.forEach(restaurant -> infos.add(RestaurantInfoDTO.fromRestaurant(restaurant)));

        return Response.ok("Success").body(infos).build();
    }

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Response> getRestaurantById(@PathVariable(name = "id") @Valid Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);

        if (restaurant.isEmpty()){
            return Response.notFound(String.format("No restaurant with id %d was found!", id)).build();
        }

        return Response.ok("Success").body(RestaurantInfoDTO.fromRestaurant(restaurant.get())).build();
    }

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/restaurants/{cuisine}")
    public ResponseEntity<Response> getRestaurantsByCuisine(@PathVariable(name = "cuisine") @Valid String cuisine) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByCuisine(cuisine);

        if (restaurants.isEmpty()){
            return Response.notFound("No restaurants were found!").build();
        }

        List<RestaurantInfoDTO> infos = new ArrayList<>();
        restaurants.forEach(restaurant -> infos.add(RestaurantInfoDTO.fromRestaurant(restaurant)));

        return Response.ok("Success").body(infos).build();
    }

}
