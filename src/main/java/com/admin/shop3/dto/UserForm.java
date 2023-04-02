package com.admin.shop3.dto;

import com.admin.shop3.entity.state.Role;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
public class UserForm {

    @Pattern(regexp = "^[a-zA-Z0-9_]{4,11}$") // 대소문자, 숫자를 포함한 5~12자 이하
    private String id;

    @Pattern(regexp = "^[a-zA-Z0-9_!@#$%^&*]{5,11}$") //대소문자, 숫자 특수문자를 포함한 6~12자 이하
    private String password;

    private Role role;


}
