package com.admin.shop3.controller;

import com.admin.shop3.dto.EventForm;
import com.admin.shop3.service.ChartService;
import com.admin.shop3.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/event/new")
    public String newEvent(@RequestBody EventForm eventForm) {
        return eventService.saveEvent(eventForm);
    }
}
