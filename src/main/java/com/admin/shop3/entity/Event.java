package com.admin.shop3.entity;

import com.admin.shop3.dto.EventResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String userName;
    private String backgroundColor;
    @Convert(converter = BooleanToYNConverter.class)
    private boolean allDay;

    @Builder
    public Event(String title, String description, String startDate, String endDate, String userName, String backgroundColor, boolean allDay) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userName = userName;
        this.backgroundColor = backgroundColor;
        this.allDay = allDay;
    }

    // DTO변환
    public EventResponseDto toDto() {
        return EventResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .start(this.startDate)
                .end(this.endDate)
                .backgroundColor(this.backgroundColor)
                .allDay(this.allDay)
                .build();
    }

}
