package com.admin.shop3.exception;

public class UserDuplicationException extends RuntimeException{


    public UserDuplicationException() { super("중복된 회원입니다.");}
}
