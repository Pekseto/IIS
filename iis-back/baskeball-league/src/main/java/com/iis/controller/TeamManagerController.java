package com.iis.controller;

import com.iis.dtos.RegisteredUserDto;
import com.iis.service.TeamManagerService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/managers")
@CrossOrigin
public class TeamManagerController {

    private final TeamManagerService teamManagerService;
    private final Mapper mapper;

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_TEAM_MANAGER') and #manager.id == authentication.principal.id")
    public ResponseEntity<RegisteredUserDto> edit(@RequestBody RegisteredUserDto manager){
        try {
            return ResponseEntity.ok(teamManagerService.edit(manager));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getManager/{id}")
    @PreAuthorize("hasRole('ROLE_TEAM_MANAGER')")
    public ResponseEntity<RegisteredUserDto> getById(@PathVariable long id){
        return ResponseEntity.ok(mapper.mapTeamManagerToDto(teamManagerService.getById(id)));
    }
}
