package com.iis.controller;

import com.iis.dto.judgeDTOs.JudgeDTO;
import com.iis.service.JudgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/judge")
@Tag(name = "Judge")
public class JudgeController {

    private final JudgeService judgeService;

    @PostMapping("/register")
    @Operation(summary = "Register new judge")
    public ResponseEntity<JudgeDTO> regiter(@RequestBody JudgeDTO judge) {
        return ResponseEntity.ok(judgeService.Register(judge));
    }

    @GetMapping("/getJudge/{id}")
    @Operation(summary = "Get judge data by id")
    public ResponseEntity<JudgeDTO> getJudge(@PathVariable("id") Long id) {
        return ResponseEntity.ok(judgeService.GetById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "Update data about judge")
    public ResponseEntity<JudgeDTO> update(@RequestBody JudgeDTO judge) {
        return ResponseEntity.ok(judgeService.Update(judge));
    }
}
