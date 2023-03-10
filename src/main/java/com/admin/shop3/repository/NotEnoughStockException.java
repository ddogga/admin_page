package com.admin.shop3.repository;

public class NotEnoughStockException extends RuntimeException{

    public NotEnoughStockException () {super("상품 제고가 없습니다.");}
    public NotEnoughStockException (String message) {super(message);}
}
