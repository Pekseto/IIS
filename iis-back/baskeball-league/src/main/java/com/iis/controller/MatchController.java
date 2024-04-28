package com.iis.controller;

import com.iis.dtos.MatchDto;
import com.iis.dtos.RefereeTeamDto;
import com.iis.service.MatchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/match")
@Tag(name="Match")
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/setRefereeTeam")
    public ResponseEntity<RefereeTeamDto> setRefereeTeam(@RequestBody RefereeTeamDto refereeTeam) {
        return ResponseEntity.ok(matchService.SetRefereeTeam(refereeTeam));
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<MatchDto>> GetAll()
    {
        return ResponseEntity.ok(matchService.GetAll());
    }
}
