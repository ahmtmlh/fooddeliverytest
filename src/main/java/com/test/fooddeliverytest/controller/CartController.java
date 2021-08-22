package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.annotation.AuthorizeUser;
import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.CartInfoDTO;
import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.Meal;
import com.test.fooddeliverytest.model.User;
import com.test.fooddeliverytest.security.UserPrincipal;
import com.test.fooddeliverytest.service.CartService;
import com.test.fooddeliverytest.service.MealService;
import com.test.fooddeliverytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class CartController {

    @Autowired
    private UserService userService;
    @Autowired
    private MealService mealService;
    @Autowired
    private CartService cartService;

    @AuthorizeUser
    @GetMapping("/cart")
    public ResponseEntity<Response> getCart(@AuthenticationPrincipal UserPrincipal principal) {

        if (principal == null || principal.getUsername().isBlank()) {
            return Response.unauthorized("Invalid user context!").build();
        }

        Optional<User> optionalUser = userService.userDetails(principal.getUsername());

        if (optionalUser.isEmpty()){
            return Response.notFound("User not found!").build();
        }

        Cart cart = optionalUser.get().getCart();

        if (cart.getMeals().isEmpty()){
            return Response.notFound("No data found in Cart").build();
        } else {
            return Response.ok("Success").body(CartInfoDTO.fromCart(cart)).build();
        }

    }

    @AuthorizeUser
    @PutMapping("/cart/add")
    public ResponseEntity<Response> addItemToCart(
            @RequestParam(name="meal_id") @Valid Long mealId,
            @RequestParam(name="count") @Valid Long count,
            @AuthenticationPrincipal UserPrincipal principal) {

        if (principal == null || principal.getUsername().isBlank()) {
            return Response.unauthorized("Invalid user context!").build();
        }

        Optional<User> optionalUser = userService.userDetails(principal.getUsername());

        if (optionalUser.isEmpty()){
            return Response.notFound("User not found!").build();
        }

        Optional<Meal> meal = mealService.getMealById(mealId);

        if (meal.isEmpty()){
            return Response.notFound("Meal not found!").build();
        }

        Cart cart = optionalUser.get().getCart();

        // If cart has a meal from another restaurant, reject insertion
        if (!cart.getMeals().isEmpty() &&
                !cart.getMeals().get(0).getRestaurant().getId().equals(meal.get().getRestaurant().getId())){
            return Response.badValue("Current cart has meals from another restaurant", "Restaurant mismatch").build();
        }

        for (int i = 0; i < count; i++)
            cart.getMeals().add(meal.get());

        cartService.updateCart(cart);

        return Response.ok("Update successful").build();
    }

    @AuthorizeUser
    @DeleteMapping("/cart/remove")
    public ResponseEntity<Response> removeItemFromCart(
            @RequestParam(name="meal_id") @Valid Long mealId,
            @RequestParam(name="count") @Valid Long count,
            @AuthenticationPrincipal UserPrincipal principal) {

        if (StringUtils.hasText(principal.getUsername())) {
            return Response.unauthorized("Invalid user context!").build();
        }

        Optional<User> optionalUser = userService.userDetails(principal.getUsername());

        if (optionalUser.isEmpty()){
            return Response.notFound("User not found!").build();
        }

        Optional<Meal> meal = mealService.getMealById(mealId);

        if (meal.isEmpty()){
            return Response.notFound("Meal not found!").build();
        }

        Cart cart = optionalUser.get().getCart();

        for (int i = 0; i < count && !cart.getMeals().isEmpty(); i++) {
            cart.getMeals().remove(meal.get());
        }

        cartService.updateCart(cart);

        return Response.ok("Update successful").build();
    }

}
