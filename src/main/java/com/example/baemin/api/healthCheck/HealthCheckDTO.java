package com.example.baemin.api.healthCheck;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class HealthCheckDTO {

    @Schema(name = "HealthCheckResponseDTO")
    @Getter
    @Builder
    public static class Response {

        @Schema(description = "서버 health 상태", example = "OK")
        private String health;

        @ArraySchema(
                schema = @Schema(description = "현재 실행 중인 profile"),
                arraySchema = @Schema(example = "[\"local\"]")
        )
        private List<String> activeProfiles;

    }

}
