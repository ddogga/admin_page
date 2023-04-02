package com.admin.shop3.dto;

import com.admin.shop3.entity.BooleanToYNConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventResponseDto {

    private Long id;
    private String title;
    private String description;
    private String start;
    private String end;
    private String backgroundColor;
    private boolean allDay;
}
