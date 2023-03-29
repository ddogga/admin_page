package com.admin.shop3.service;

import com.admin.shop3.dto.EventForm;
import com.admin.shop3.entity.Event;
import com.admin.shop3.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public String saveEvent(EventForm eventForm){

        Event event = Event.builder()
                .allDay(eventForm.isAllDay())
                .title(eventForm.getTitle())
                .startDate(eventForm.getStart())
                .endDate(eventForm.getEnd())
                .backgroundColor(eventForm.getBackgroundColor())
                .description(eventForm.getDescription())
                .userName(eventForm.getUserName())
                .build();
        eventRepository.save(event);
        return "이벤트 등록";
    }
}
