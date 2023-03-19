package com.example.baemin.api.healthCheck.service;

import com.example.baemin.api.healthCheck.dto.HealthCheckDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final Environment environment;

    public HealthCheckDTO.HealthCheckReadResponse read() {
        return HealthCheckDTO.HealthCheckReadResponse.builder()
                .health("OK")
                .activeProfiles(getActiveProfiles())
                .build();
    }

    private List<String> getActiveProfiles() {
        return Arrays.asList(environment.getActiveProfiles());
    }

}
