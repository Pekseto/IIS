package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
@CrossOrigin
public class PlayerController {

    private final PlayerService playerService;

    @PutMapping("/edit")
    public ResponseEntity<RegisteredUserDto> edit(@RequestBody RegisteredUserDto player){
        try {
            return ResponseEntity.ok(playerService.edit(player));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
