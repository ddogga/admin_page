package com.admin.shop3.controller;

import com.admin.shop3.dto.EventForm;
import com.admin.shop3.dto.EventRequestDto;
import com.admin.shop3.dto.EventResponseDto;
import com.admin.shop3.repository.EventRepository;
import com.admin.shop3.service.ChartService;
import com.admin.shop3.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j

public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    @PostMapping("/event/new")
    public String newEvent(@RequestBody EventForm eventForm) {
        return eventService.saveEvent(eventForm);
    }

    @GetMapping("/events")
    public ResponseEntity getEvents(EventRequestDto eventRequestDto){
        log.info("start_date: " + eventRequestDto.getStartDate());
        log.info("end_date: " + eventRequestDto.getEndDate());
        log.info("user_name: " + eventRequestDto.getUserName());
        return ResponseEntity.of(Optional.ofNullable(eventService.getEvents(eventRequestDto)));
    }

    @DeleteMapping("/event")
    public String deleteEvent (Long eventId) {
        log.info("delete eventId : " + eventId);
        try {
            eventRepository.deleteById(eventId);
            return "이벤트 삭제 완료";
        } catch (Exception e){
            log.error("Event Delete Error : " + e.getMessage());
            return "존재하지 않는 이벤트 입니다.";
        }
    }
}
