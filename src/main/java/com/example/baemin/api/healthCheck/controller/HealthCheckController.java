package com.example.baemin.api.healthCheck.controller;

import com.example.baemin.api.healthCheck.dto.HealthCheckDTO;
import com.example.baemin.api.healthCheck.service.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @GetMapping("")
    public ResponseEntity<HealthCheckDTO.HealthCheckReadResponse> read() {
        return ResponseEntity.ok(healthCheckService.read());
    }
}
