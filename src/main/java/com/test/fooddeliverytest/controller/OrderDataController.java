package com.test.fooddeliverytest.controller;

import com.test.fooddeliverytest.annotation.AuthorizeUser;
import com.test.fooddeliverytest.controller.response.Response;
import com.test.fooddeliverytest.dto.OrderDataInfoDTO;
import com.test.fooddeliverytest.model.Cart;
import com.test.fooddeliverytest.model.OrderData;
import com.test.fooddeliverytest.model.User;
import com.test.fooddeliverytest.security.UserPrincipal;
import com.test.fooddeliverytest.service.CartService;
import com.test.fooddeliverytest.service.MealService;
import com.test.fooddeliverytest.service.OrderService;
import com.test.fooddeliverytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class OrderDataController {

    @Autowired
    private UserService userService;
    @Autowired
    private MealService mealService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @AuthorizeUser
    @PutMapping("/orders/create")
    public ResponseEntity<Response> createOrder(@AuthenticationPrincipal UserPrincipal principal) {

        if (principal == null || principal.getUsername().isBlank()) {
            return Response.unauthorized("Invalid user context!").build();
        }

        Optional<User> optionalUser = userService.userDetails(principal.getUsername());

        if (optionalUser.isEmpty()){
            return Response.notFound("User not found!").build();
        }

        Cart cart = optionalUser.get().getCart();

        if (cart.getMeals().isEmpty()){
            return Response.notFound("User cart is empty!").build();
        }

        OrderData orderData = new OrderData();

        orderData.setMealData(cart.getMeals());
        orderData.setRestaurant(cart.getMeals().get(0).getRestaurant());
        orderData.setUser(optionalUser.get());

        orderService.createOrder(orderData);

        // Clear cart data after creating orderData
        cart.getMeals().clear();
        cartService.updateCart(cart);

        return Response.ok("Success").build();
    }

    @AuthorizeUser
    @GetMapping("/orders")
    public ResponseEntity<Response> getOrders(@AuthenticationPrincipal UserPrincipal principal){
        if (principal == null || principal.getUsername().isBlank()) {
            return Response.unauthorized("Invalid user context!").build();
        }

        Optional<User> optionalUser = userService.userDetails(principal.getUsername());

        if (optionalUser.isEmpty()){
            return Response.notFound("User not found!").build();
        }

        //List<OrderData> orderList = orderService.getOrdersOfUser(optionalUser.get());
        List<OrderData> orderList = optionalUser.get().getUserData().getLastOrders();

        if (orderList.isEmpty()){
            return Response.notFound("No order found for the user!").build();
        }

        List<OrderDataInfoDTO> orderInfoList = new ArrayList<>();
        orderList.forEach(orderData -> orderInfoList.add(OrderDataInfoDTO.fromOrderData(orderData)));

        return Response.ok("Success").body(orderInfoList).build();

    }

}
