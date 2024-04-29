package com.iis.controller;

import com.iis.dtos.MatchRosterDto;
import com.iis.model.MatchRoster;
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

    //Test funkcija
    @GetMapping("/setBenchPlayer")
    public ResponseEntity<MatchRosterDto> setBenchPlayer(){
        return ResponseEntity.ok(service.SetBenchPlayer());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MatchRosterDto> getById(@PathVariable long id){
        return ResponseEntity.ok(service.GetMatchRoster(id));
    }
}
