package com.admin.shop3.entity;


import com.admin.shop3.dto.OrderItemDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
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



    // =============================

    @Builder
    public OrderItem(int orderItemPrice, int count, Order order, Item item) {
        this.orderItemPrice = orderItemPrice;
        this.count = count;
        this.order = order;
        this.item = item;

    }

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderItemPrice(orderPrice)
                .count(count)
                .build();

        item.removeStock(count); //주문 들어온 수량 만큼 재고에서 빼기
        item.increaseSales(count);
        return orderItem;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    // DTO변환
    public OrderItemDto toDto() {
        return OrderItemDto.builder()
                .orderItemId(this.id)
                .ItemName(this.item.getName())
                .orderItemPrice(this.orderItemPrice)
                .count(this.count)
                .build();
    }
}
