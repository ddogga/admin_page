package com.admin.shop3.controller;

import com.admin.shop3.dto.CancelOrderUpdateReqDto;
import com.admin.shop3.dto.OrderCancelReqDto;
import com.admin.shop3.dto.OrderForm;
import com.admin.shop3.dto.OrderStatusUpdateReqDto;
import com.admin.shop3.entity.Order;
import com.admin.shop3.entity.state.OrderStatus;
import com.admin.shop3.service.ItemService;
import com.admin.shop3.service.OrderService;
import com.admin.shop3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;


    @PostMapping("/order")
    public String order(@RequestBody OrderForm orderForm) {

        return orderService.order(orderForm);
    }

    @GetMapping("/orders")
    public ResponseEntity getOrders(@RequestParam int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getOrders(pageRequest));
    }

    @GetMapping("/order/items")
    public ResponseEntity getOrderItems(Long id) {
        return ResponseEntity.ok(orderService.getOrderItemDtoList(id));
    }


    @PutMapping("/order/status")
    public String updateStatus(@RequestBody OrderStatusUpdateReqDto dto) {
        return orderService.updateStatus(dto);
    }

    @PutMapping("/order/cancel")
    public String cancelOrder(@RequestBody OrderCancelReqDto dto) {
        return orderService.cancelOrder(dto);
    }

    @GetMapping("/orders/cancel")
    public ResponseEntity getCancelOrders(@RequestParam int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getCancelOrders(pageRequest));
    }

    @PutMapping("/order/cancel/reason")
    public String updateCancelOrder(@RequestBody CancelOrderUpdateReqDto dto) {
        return orderService.updateCancelOrder(dto);
    }

    @GetMapping("/orders/now")
    public ResponseEntity getCurrentOrders() {
        return ResponseEntity.of(Optional.ofNullable(orderService.getCurrentOrders()));
    }


}
