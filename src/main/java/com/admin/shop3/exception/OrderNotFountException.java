package com.admin.shop3.exception;

public class OrderNotFountException extends RuntimeException{

    public OrderNotFountException() { super("주문 정보를 찾을 수 없습니다.");
    }
}
