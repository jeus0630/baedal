package com.example.baemin.api.healthCheck;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health check", description = "서버 상태 체크 API")
@RestController
@RequestMapping("/health-check")
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @Operation(summary = "서버 Health Check API", description = "현재 서버가 정상적으로 기동이 된 상태인지 확인하는 API")
    @GetMapping("")
    public ResponseEntity<HealthCheckDTO.Response> read() {
        return ResponseEntity.ok(healthCheckService.read());
    }
}
