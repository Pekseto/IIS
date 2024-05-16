package com.iis.controller;

import com.iis.dtos.MatchResultDto;
import com.iis.service.MatchResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/match-result")
@Tag(name="Match Result")
public class MatchResultController {

    private final MatchResultService matchResultService;

    @GetMapping
    public ResponseEntity<List<MatchResultDto>> getAllMatchResults() {
        List<MatchResultDto> matchResults = matchResultService.getAll();
        return new ResponseEntity<>(matchResults, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResultDto> getMatchResultById(@PathVariable Long id) {
        MatchResultDto matchResult = matchResultService.getById(id);
        return new ResponseEntity<>(matchResult, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatchResultDto> saveMatchResult(@RequestBody MatchResultDto matchResultDTO) {
        MatchResultDto savedMatchResult = matchResultService.save(matchResultDTO);
        return new ResponseEntity<>(savedMatchResult, HttpStatus.CREATED);
    }
}
