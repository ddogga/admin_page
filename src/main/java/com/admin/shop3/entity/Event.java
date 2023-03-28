package com.admin.shop3.entity;

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
        private String username;
        private String backgroundColor;
        @Convert(converter = BooleanToYNConverter.class)
        private boolean allDay;

        @Builder
        public Event(String title, String description, String startDate, String endDate, String username, String backgroundColor, boolean allDay) {
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
            this.username = username;
            this.backgroundColor = backgroundColor;
            this.allDay = allDay;
        }
}
