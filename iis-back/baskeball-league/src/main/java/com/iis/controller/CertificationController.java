package com.iis.controller;

import com.iis.dtos.CertificateDto;
import com.iis.dtos.CertificationStatusDataIn;
import com.iis.model.Certificate;
import com.iis.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certification")
@Tag(name = "Certification")
public class CertificationController {

    private final CertificateService certificateService;

    @PostMapping("/changeStatus")
    @Operation(summary = "Change status to accepted or declined for certification request")
    public ResponseEntity<String> changeStatus(@RequestBody CertificationStatusDataIn dataIn) {
        certificateService.ChangeStatus(dataIn);
        return ResponseEntity.ok("");
    }

    @GetMapping("/getAllRequests")
    @Operation(summary = "Get all certificaiton requests")
    public ResponseEntity<List<CertificateDto>> getAllRequests() {
        return ResponseEntity.ok(certificateService.GetAllRequests());
    }

    @GetMapping("/getAllCertificates/{userId}")
    @Operation(summary = "Get all certificates for user")
    public ResponseEntity<List<CertificateDto>> getAllCertificates(@PathVariable Long userId) {
        return ResponseEntity.ok(certificateService.GetAllCertificates(userId));
    }
}
