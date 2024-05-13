package com.iis.controller;

import com.iis.dtos.RecordKeeperDto;
import com.iis.dtos.MatchDto;
import com.iis.dtos.RefereeTeamDto;
import com.iis.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ROLE_LEAGUE_ADMIN', 'ROLE_REFEREE')")
    public ResponseEntity<List<MatchDto>> GetAll()
    {
        return ResponseEntity.ok(matchService.GetAll());
    }

    @GetMapping("/getById/{matchId}")
    public ResponseEntity<MatchDto> GetById(@PathVariable long matchId){
        return ResponseEntity.ok(matchService.GetById(matchId));
    }

    @GetMapping("/delegateRecordKeeper/{matchId}/{recordKeeperId}")
    public ResponseEntity<MatchDto> DelegateRecordKeeper(@PathVariable long matchId, @PathVariable long recordKeeperId){
        return ResponseEntity.ok(matchService.DelegateRecordKeeper(matchId, recordKeeperId));
    }
    
    @GetMapping("/getMatch/{id}")
    @Operation(summary = "Get match data")
    @PreAuthorize("hasAnyRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<MatchDto> GetMatch(@PathVariable long id)
    {
        return ResponseEntity.ok(matchService.GetMatch(id));
    }
}
