package com.admin.shop3.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException() {super("존재하지 않는 상품입니다.");}
}
