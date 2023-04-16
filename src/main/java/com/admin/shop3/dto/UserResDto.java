package com.admin.shop3.dto;


import com.admin.shop3.entity.state.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResDto {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private Role role;
}
