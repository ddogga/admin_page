package com.admin.shop3.dto;

import com.admin.shop3.entity.OrderItem;
import com.admin.shop3.entity.state.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {

    private Long id;
    private String userName;
//    private List<OrderItemDto> orderItems;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private OrderStatus status;
    private int totalPrice;
    private int elapsedTime;
}
