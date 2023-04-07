package com.admin.shop3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDto {

    private Long id;
    private String ItemName;
    private int orderItemPrice;
    private int count;



}
