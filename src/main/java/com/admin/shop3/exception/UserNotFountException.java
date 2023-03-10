package com.admin.shop3.exception;

public class UserNotFountException extends RuntimeException{

    public UserNotFountException() { super("회원을 찾을 수 없습니다.");}
}
