package com.admin.shop3.service;

import com.admin.shop3.dto.EventForm;
import com.admin.shop3.dto.EventRequestDto;
import com.admin.shop3.dto.EventResponseDto;
import com.admin.shop3.entity.Event;
import com.admin.shop3.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<EventResponseDto> getMonthlyEvents(EventRequestDto eventRequestDto) {
        List<Event> events = eventRepository.findEventBetweenDateWithJPQL(eventRequestDto.getStartDate(),eventRequestDto.getEndDate(),eventRequestDto.getUserName());
        return events
                .stream()
                .map(Event::toDto).toList();
    }

    public List<EventResponseDto> getWeeklyEvents(String userName) {
        LocalDate now = LocalDate.now();
        int day = now.getDayOfWeek().getValue();
        //이번주 일요일 날짜를 가져옴.
        LocalDate end = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth() + (7-day));
        List<Event> events = eventRepository.findEventBetweenDateWithJPQL(now.toString(),end.toString(),userName);
        return events
                .stream()
                .map(Event::toDto).toList();
    }



}
