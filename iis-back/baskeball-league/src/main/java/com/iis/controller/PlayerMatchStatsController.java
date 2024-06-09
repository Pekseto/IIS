package com.iis.controller;

import com.iis.dtos.MatchDto;
import com.iis.dtos.PlayerMatchStatsDto;
import com.iis.service.PlayerMatchStatsService;
import com.lowagie.text.DocumentException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/player-match-stats")
@Tag(name="Player Match Stats")
public class PlayerMatchStatsController {
    private final PlayerMatchStatsService service;

    @PostMapping("/createPlayerMatchStats")
    public ResponseEntity<MatchDto> createPlayerMatchStats(@RequestBody MatchDto matchDto){
        return ResponseEntity.ok(service.CreatePlayerMatchStats(matchDto));
    }

    @GetMapping("/getAllForTeamOnMatch/{matchId}/{teamId}")
    @PermitAll
    public ResponseEntity<List<PlayerMatchStatsDto>> getAllForTeamOnMatch(@PathVariable long matchId, @PathVariable long teamId){
        return ResponseEntity.ok(service.GetAllForTeamOnMatch(matchId, teamId));
    }

    @GetMapping("/getActivePlayersOnMatch/{matchId}/{homeRosterId}/{awayRosterId}")
    public ResponseEntity<List<PlayerMatchStatsDto>> getForActivePlayersOnMatch(@PathVariable long matchId, @PathVariable long homeRosterId, @PathVariable long awayRosterId){
        return ResponseEntity.ok(service.GetStatsForActivePlayersOnMatch(matchId, homeRosterId, awayRosterId));
    }

    @PutMapping("/updatePlayersTimePlayed")
    public void updatePlayersTimePlayed(@RequestBody List<PlayerMatchStatsDto> playersStats){
        service.UpdatePlayersTimePlayed(playersStats);
    }

    @GetMapping(value = "generatePdf/{matchId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long matchId) throws IOException {
        try {
            byte[] pdfContent = service.exportPdf(matchId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "matchReport.pdf");

            return ResponseEntity.ok().headers(headers).body(pdfContent);
        } catch (IOException | DocumentException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
