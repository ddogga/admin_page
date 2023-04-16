package com.admin.shop3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CancelOrderDto {

    private Long cancelId;
    private Long orderId;
    private String userName;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private String cancelTime;
    private int totalPrice;
    private String reason;
}
