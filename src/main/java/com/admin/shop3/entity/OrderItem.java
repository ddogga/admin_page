package com.admin.shop3.entity;


import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "order_item_price")
    private int orderItemPrice;
    private int count; //주문 수량

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private  Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
