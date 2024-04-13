package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.service.CoachService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coaches")
@CrossOrigin
public class CoachController {

    private final CoachService coachService;
    private final Mapper mapper;

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_COACH') and #coach.id == authentication.principal.id")
    public ResponseEntity<RegisteredUserDto> edit(@RequestBody RegisteredUserDto coach){
        try {
            return ResponseEntity.ok(coachService.edit(coach));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getCoach/{id}")
    @PreAuthorize("hasRole('ROLE_COACH')")
    public ResponseEntity<RegisteredUserDto> getById(@PathVariable long id){
        return ResponseEntity.ok(mapper.mapCoachToDto(coachService.getById(id)));
    }
}
