package com.iis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iis.dtos.RegularSeasonScheduleDto;
import com.iis.service.RegularSeasonScheduleService;
import com.iis.util.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@Tag(name = "Regular season schedule")
public class RegularSeasonScheduleController {

    private final RegularSeasonScheduleService scheduleService;
    private final ObjectMapper objectMapper;

    @PostMapping("/generate")
    @Operation(summary = "Generate season schedule")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RegularSeasonScheduleDto> generate() {
        return ResponseEntity.ok(scheduleService.generateSchedule());
    }

    @PostMapping("/save")
    @Operation(summary = "Save season schedule")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RegularSeasonScheduleDto> save(@RequestBody Object schedule) {
        RegularSeasonScheduleDto scheduleToSave = objectMapper.convertValue(schedule,RegularSeasonScheduleDto.class);
        RegularSeasonScheduleDto result = scheduleService.save(scheduleToSave);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/exists/{year}")
    @Operation(summary = "Check if schedule exists")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<Boolean> checkIfScheduleAlreadyExists(@PathVariable int year) {
        return ResponseEntity.ok(scheduleService.checkIfScheduleAlreadyExists(year));
    }

    @GetMapping("/get-this-year-schedule/{year}")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RegularSeasonScheduleDto> get(@PathVariable int year) {
        return ResponseEntity.ok(scheduleService.getThisYearSchedule(year));
    }
}
