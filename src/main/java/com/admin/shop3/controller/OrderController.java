package com.admin.shop3.controller;

import com.admin.shop3.dto.OrderForm;
import com.admin.shop3.dto.OrderStatusUpdateReqDto;
import com.admin.shop3.entity.Order;
import com.admin.shop3.entity.state.OrderStatus;
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

    @GetMapping("/orders")
    public ResponseEntity getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }


    @PutMapping("/order_status")
    public String updateStatus(@RequestBody OrderStatusUpdateReqDto dto) {
        return orderService.updateStatus(dto);
    }



}
