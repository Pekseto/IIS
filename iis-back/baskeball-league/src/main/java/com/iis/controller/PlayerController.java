package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.service.PlayerService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
@CrossOrigin
public class PlayerController {

    private final PlayerService playerService;
    private final Mapper mapper;

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_PLAYER') and #player.id == authentication.principal.id")
    public ResponseEntity<RegisteredUserDto> edit(@RequestBody RegisteredUserDto player){
        try {
            return ResponseEntity.ok(playerService.edit(player));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPlayer/{id}")
    @PreAuthorize("hasRole('ROLE_PLAYER')")
    public ResponseEntity<RegisteredUserDto> getById(@PathVariable long id){
        return ResponseEntity.ok(mapper.mapPlayerToDto(playerService.getById(id)));
    }
}
