package com.admin.shop3.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderForm {

    private Long userId;
    private List<OrderItemForm> orderedItems;

}
