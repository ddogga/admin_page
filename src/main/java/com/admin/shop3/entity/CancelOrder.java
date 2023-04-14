package com.admin.shop3.entity;


import com.admin.shop3.dto.CancelOrderDto;
import com.admin.shop3.dto.OrderItemDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public CancelOrder(Order order, String reason) {
        this.order = order;
        this.reason = reason;
    }

    public CancelOrderDto toDto() {
        return CancelOrderDto.builder()
                .cancelId(this.id)
                .orderId(this.order.getId())
                .userName(this.order.getUser().getName())
                .orderDate(this.order.getOrderDate())
                .orderTime(this.order.getOrderTime())
                .cancelTime(this.getCreatedDate())
                .totalPrice(this.order.getTotalPrice())
                .reason(this.reason)
                .build();
    }

    private List<OrderItemDto> toDtoList() {
        return this.order.getOrderItems()
                .stream()
                .map(OrderItem::toDto).toList();
    }


    // 생성 메서드
    public static CancelOrder createCancelOrder(Order order, String reason){
        CancelOrder cancelOrder = CancelOrder.builder()
                .reason(reason)
                .build();
        cancelOrder.setOrder(order);
        return cancelOrder;
    }

    /**
     * 연관관계 편의 메서드
     * @param order
     */

    public void setOrder(Order order){
        this.order = order;
        order.setCancelOrder(this);
    }

    public void update(String reason) {
        this.reason = reason;
    }

}
