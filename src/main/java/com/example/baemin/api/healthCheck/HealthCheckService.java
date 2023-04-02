package com.example.baemin.api.healthCheck;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final Environment environment;

    public HealthCheckDTO.Response read() {
        return HealthCheckDTO.Response.builder()
                .health("OK")
                .activeProfiles(getActiveProfiles())
                .build();
    }

    private List<String> getActiveProfiles() {
        return Arrays.asList(environment.getActiveProfiles());
    }

}
