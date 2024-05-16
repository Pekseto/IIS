package com.iis.controller;

import com.iis.dtos.MatchStateDto;
import com.iis.service.MatchStateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/match-state")
@Tag(name="Match States")
public class MatchStateController {

    private final MatchStateService service;

    @PostMapping("/addMatchState")
    public ResponseEntity<MatchStateDto> addMatchState(@RequestBody MatchStateDto matchStateDto){
        return ResponseEntity.ok(service.AddMatchState(matchStateDto));
    }

    @GetMapping("/getByMatchId/{matchId}")
    public ResponseEntity<MatchStateDto> getByMatchId(@PathVariable long matchId){
        return ResponseEntity.ok(service.GetByMatchId(matchId));
    }
}
