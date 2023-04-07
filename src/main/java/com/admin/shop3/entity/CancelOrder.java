package com.admin.shop3.entity;


import com.admin.shop3.dto.CancelOrderDto;
import com.admin.shop3.dto.OrderItemDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class CancelOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "cancel_order_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String reason;


    @Builder
    public CancelOrder(Long id, Order order, String reason) {
        this.id = id;
        this.order = order;
        this.reason = reason;
    }

    public CancelOrderDto toDto() {
        return CancelOrderDto.builder()
                .id(this.id)
                .userName(this.order.getUser().getName())
                .orderDate(this.order.getOrderDate())
                .orderTime(this.order.getOrderTime())
                .cancelTime(this.getCreatedDate())
                .totalPrice(this.order.getTotalPrice())
                .orderItems(toDtoList())
                .reason(this.reason)
                .build();
    }

    private List<OrderItemDto> toDtoList() {
        return this.order.getOrderItems()
                .stream()
                .map(OrderItem::toDto).toList();
    }

    public void update(String reason) {
        this.reason = reason;
    }
}
