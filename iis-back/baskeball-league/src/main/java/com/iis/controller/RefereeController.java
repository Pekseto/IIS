package com.iis.controller;

import com.iis.dto.judgeDTOs.RefereeDTO;
import com.iis.service.RefereeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/referee")
@Tag(name = "Referee")
public class RefereeController {

    private final RefereeService refereeService;

    @PostMapping("/register")
    @Operation(summary = "Register new referee")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RefereeDTO> regiter(@RequestBody RefereeDTO referee) {
        return ResponseEntity.ok(refereeService.Register(referee));
    }

    @GetMapping("/getReferee/{id}")
    @Operation(summary = "Get referee data by id")
    public ResponseEntity<RefereeDTO> getJudge(@PathVariable("id") Long id) {
        return ResponseEntity.ok(refereeService.GetById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "Update data about referee")
    public ResponseEntity<RefereeDTO> update(@RequestBody RefereeDTO judge) {
        return ResponseEntity.ok(refereeService.Update(judge));
    }
}
