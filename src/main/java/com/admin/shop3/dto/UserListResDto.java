package com.admin.shop3.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
public class UserListResDto {

    private Page<UserResDto> userList;
    private Long count;
}
