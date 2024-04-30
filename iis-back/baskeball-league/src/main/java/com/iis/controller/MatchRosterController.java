package com.iis.controller;

import com.iis.dtos.MatchRosterDto;
import com.iis.service.MatchRosterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/match-roster")
@Tag(name="Match Roster")
public class MatchRosterController {
    private final MatchRosterService service;

    @GetMapping("/getById/{id}")
    public ResponseEntity<MatchRosterDto> getById(@PathVariable long id){
        return ResponseEntity.ok(service.GetMatchRoster(id));
    }

    @PutMapping("/addToBench/{id}/{playerId}")
    public ResponseEntity<MatchRosterDto> addToBench(@PathVariable long id, @PathVariable long playerId){
        return ResponseEntity.ok(service.AddToBench(id, playerId));
    }

    @PutMapping("/addToStartingFive/{id}/{playerId}")
    public ResponseEntity<MatchRosterDto> addToStartingFive(@PathVariable long id, @PathVariable long playerId){
        return ResponseEntity.ok(service.AddToStartingFive(id, playerId));
    }

    @PutMapping("/substitute/{id}/{inId}/{outId}")
    public ResponseEntity<MatchRosterDto> substitute(@PathVariable long id, @PathVariable long inId, @PathVariable long outId){
        return ResponseEntity.ok(service.Substitute(id, inId, outId));
    }
}
