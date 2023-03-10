package com.admin.shop3.controller;

import com.admin.shop3.dto.OrderForm;
import com.admin.shop3.service.ItemService;
import com.admin.shop3.service.OrderService;
import com.admin.shop3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;


    @PostMapping("/order")
    public ResponseEntity order(@RequestBody OrderForm orderForm) {


        return ResponseEntity.ok(orderService.order(orderForm));
    }
}
