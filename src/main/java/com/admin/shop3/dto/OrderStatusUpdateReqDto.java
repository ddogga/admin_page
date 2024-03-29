package com.admin.shop3.dto;

import com.admin.shop3.entity.state.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderStatusUpdateReqDto {
    private OrderStatus status;
    private Long orderId;
}
