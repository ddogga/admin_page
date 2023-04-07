package com.admin.shop3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelOrderUpdateReqDto {

    private Long id;
    private String reason;
}
