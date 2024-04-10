package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.service.TeamManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/managers")
@CrossOrigin
public class TeamManagerController {

    private final TeamManagerService teamManagerService;

    @PutMapping("/edit")
    public ResponseEntity<RegisteredUserDto> edit(@RequestBody RegisteredUserDto manager){
        try {
            return ResponseEntity.ok(teamManagerService.edit(manager));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
