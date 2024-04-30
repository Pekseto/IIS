package com.iis.controller;

import com.iis.dtos.MatchEventDto;
import com.iis.service.MatchEventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/match-event")
@Tag(name="Match Event")
public class MatchEventController {
    private final MatchEventService service;

    @PostMapping("/addEvent")
    @PermitAll
    public ResponseEntity<MatchEventDto> addEvent(@RequestBody MatchEventDto eventDto){
        return ResponseEntity.ok(service.AddEvent(eventDto));
    }
}
