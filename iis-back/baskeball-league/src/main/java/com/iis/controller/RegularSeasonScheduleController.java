package com.iis.controller;

import com.iis.dtos.RegularSeasonScheduleDto;
import com.iis.service.RegularSeasonScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@Tag(name = "Regular season schedule")
public class RegularSeasonScheduleController {

    private final RegularSeasonScheduleService scheduleService;

    @PostMapping("/generate")
    @Operation(summary = "Generate season schedule")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RegularSeasonScheduleDto> generate() {
        return ResponseEntity.ok(scheduleService.generateSchedule());
    }
}
