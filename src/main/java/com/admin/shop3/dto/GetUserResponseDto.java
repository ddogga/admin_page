package com.admin.shop3.dto;

import com.admin.shop3.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUserResponseDto {

    private List<User> users;
    private int user_count;
}
