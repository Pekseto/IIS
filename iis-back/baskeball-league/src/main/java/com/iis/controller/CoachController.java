package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coaches")
@CrossOrigin
public class CoachController {

    private final CoachService coachService;

    @PutMapping("/edit")
    public ResponseEntity<RegisteredUserDto> edit(@RequestBody RegisteredUserDto coach){
        try {
            return ResponseEntity.ok(coachService.edit(coach));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
