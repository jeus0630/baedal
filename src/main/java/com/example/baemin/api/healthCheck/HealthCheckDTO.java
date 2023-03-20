package com.example.baemin.api.healthCheck;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class HealthCheckDTO {

    @Getter
    @Builder
    public static class HealthCheckReadResponse {
        private String health;
        private List<String> activeProfiles;
    }

}
