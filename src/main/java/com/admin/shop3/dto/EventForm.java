package com.admin.shop3.dto;

import com.admin.shop3.entity.BooleanToYNConverter;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventForm {

    private boolean allDay;
    private String title;
    private String start;
    private String end;
    private String backgroundColor;
    private String description;
    private String userName;



}
