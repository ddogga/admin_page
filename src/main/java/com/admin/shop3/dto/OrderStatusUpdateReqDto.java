package com.admin.shop3.dto;

import com.admin.shop3.entity.state.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusUpdateReqDto {
    private OrderStatus status;
    private Long orderId;
}
