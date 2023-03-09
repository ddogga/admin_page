package com.admin.shop3.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
public class UserForm {

    @Pattern(regexp = "^[a-zA-Z0-9]{4,11}$") // 대소문자, 숫자를 포함한 5~12자 이하
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9_!@#$%^&*]{5,12}$") //대소문자, 숫자 특수문자를 포함한 6~13자 이하
    private String password;


}
