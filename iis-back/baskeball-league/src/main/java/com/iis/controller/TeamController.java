package com.iis.controller;

import com.iis.dtos.TeamDto;
import com.iis.model.Player;
import com.iis.model.Team;
import com.iis.service.TeamService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    private final Mapper mapper;

    @PostMapping("/register")
    //@PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<Team> register(@RequestBody TeamDto team){
        try {
            return ResponseEntity.ok(teamService.register(team));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TeamDto>> getAll(){
        try {
            return ResponseEntity.ok(mapper.mapList(teamService.getAll(),TeamDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/getById/{id}")
    //@PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN', 'ROLE_RECORD_KEEPER', 'ROLE_TEAM_MANAGER')")
    public ResponseEntity<TeamDto> getById(@PathVariable long id){
        try {
            return ResponseEntity.ok(teamService.getTeamById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
