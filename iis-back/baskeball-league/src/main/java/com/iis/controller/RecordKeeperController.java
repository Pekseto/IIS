package com.iis.controller;

import com.iis.dtos.RecordKeeperDto;
import com.iis.service.RecordKeeperService;
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
@RequestMapping("/api/record-keeper")
@Tag(name = "RecordKeeper")
public class RecordKeeperController {
    private final RecordKeeperService service;

    @PostMapping("/register")
    @Operation(summary = "Register new record keeper")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<RecordKeeperDto> register(@RequestBody RecordKeeperDto recordKeeperDTO){
        return ResponseEntity.ok(service.Register(recordKeeperDTO));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get record keeper by ID")
    public ResponseEntity<RecordKeeperDto> get(@PathVariable long id){
        return ResponseEntity.ok(service.GetById(id));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_LEAGUE_ADMIN')")
    public ResponseEntity<List<RecordKeeperDto>> getAll(){
        return ResponseEntity.ok(service.GetAll());
    }

    @PutMapping("/edit")
    @Operation(summary =  "Edit record keeper")
    public ResponseEntity<RecordKeeperDto> edit(@RequestBody RecordKeeperDto recordKeeperDTO){
        return ResponseEntity.ok(service.Update(recordKeeperDTO));
    }
}
