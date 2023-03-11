package com.admin.shop3.entity;


import com.admin.shop3.entity.state.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    // =====================

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_price")
    private int totalPrice;

    @Builder
    public Order(User user, LocalDateTime orderTime, OrderStatus status, int totalPrice) {
        this.user = user;
        this.orderTime = orderTime;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // 생성 메서드
    public static Order createOrder(User user, List<OrderItem> orderItems, int totalPrice) {


        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.ORDER)
                .orderTime(LocalDateTime.now())
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
}
