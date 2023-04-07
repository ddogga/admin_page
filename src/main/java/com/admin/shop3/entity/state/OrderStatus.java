package com.admin.shop3.entity.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

public enum OrderStatus {

    ORDER("주문 접수"),
    PICKING("물건 담는중"),
    PACKING("물건 포장중"),
    DELIVERY("배달중"),
    FINISH("주문 완료"),
    CANCEL("주문 취소");

    private final String title;


    OrderStatus(String title){
        this.title = title;
    }



}