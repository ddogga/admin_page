package com.admin.shop3.entity;


import com.admin.shop3.dto.OrderDto;
import com.admin.shop3.dto.OrderItemDto;
import com.admin.shop3.entity.state.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor

public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    // =========연관관계============
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "delivery_id")
//    private Delivery delivery;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // ===========================


    // ======== 주문 시간 ===========
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_time")
    private LocalTime orderTime;


    // =============================

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_price")
    private int totalPrice;

    @Builder
    public Order(User user, LocalDate orderDate, LocalTime orderTime, OrderStatus status, int totalPrice) {
        this.user = user;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // 생성 메서드
    public static Order createOrder(User user, List<OrderItem> orderItems, int totalPrice) {

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.ORDER)
                .orderDate(LocalDate.now())
                .orderTime(LocalTime.now())
                .totalPrice(totalPrice)
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    //연관관계 편의 메서드


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // DTO 변환
    public OrderDto toDto() {
        return OrderDto.builder()
                .id(this.id)
                .userName(this.user.getName())
                .orderItems(toDtoList())
                .orderDate(this.orderDate)
                .orderTime(this.orderTime)
                .status(this.status)
                .totalPrice(this.totalPrice)
                .build();
    }

    private List<OrderItemDto> toDtoList() {
        return this.orderItems
                .stream()
                .map(OrderItem::toDto).toList();
    }

    /**
     * update status
     */
    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
