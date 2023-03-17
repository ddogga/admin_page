package com.admin.shop3.controller;

import com.admin.shop3.dto.OrderForm;
import com.admin.shop3.entity.Order;
import com.admin.shop3.service.ItemService;
import com.admin.shop3.service.OrderService;
import com.admin.shop3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;


    @PostMapping("/order/new")
    public String order(@RequestBody OrderForm orderForm) {


        return orderService.order(orderForm);
    }


}
