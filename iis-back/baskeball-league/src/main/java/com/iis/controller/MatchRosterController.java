package com.iis.controller;

import com.iis.dtos.MatchDto;
import com.iis.dtos.MatchRosterDto;
import com.iis.service.MatchRosterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/removeFromBench/{id}/{playerId}")
    public ResponseEntity<MatchRosterDto> removeFromBench(@PathVariable long id, @PathVariable long playerId){
        return ResponseEntity.ok(service.RemoveFromBench(id, playerId));
    }

    @PutMapping("/addToStartingFive/{id}/{playerId}")
    public ResponseEntity<MatchRosterDto> addToStartingFive(@PathVariable long id, @PathVariable long playerId){
        return ResponseEntity.ok(service.AddToStartingFive(id, playerId));
    }

    @PutMapping("/removeFromStartingFive/{id}/{playerId}")
    public ResponseEntity<MatchRosterDto> removeFromStartingFive(@PathVariable long id, @PathVariable long playerId){
        return ResponseEntity.ok(service.RemoveFromStartingFive(id, playerId));
    }

    @PostMapping("/createMatchRostersForMatch")
    //@PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN', 'ROLE_RECORD_KEEPER')")
    public ResponseEntity<MatchDto> createMatchRostersForMatch(@RequestBody MatchDto matchDto){
        return ResponseEntity.ok(service.CreateMatchRostersForMatch(matchDto));
    }

    @PutMapping("/substitute/{id}/{inId}/{outId}")
    public ResponseEntity<MatchRosterDto> substitute(@PathVariable long id, @PathVariable long inId, @PathVariable long outId){
        return ResponseEntity.ok(service.Substitute(id, inId, outId));
    }
}
