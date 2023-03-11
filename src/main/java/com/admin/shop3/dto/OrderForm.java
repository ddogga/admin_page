package com.admin.shop3.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderForm {

    private Long userId;
    private List<OrderItemForm> orderedItems;

}
