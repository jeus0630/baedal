package com.example.baemin.domain.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class RestaurantDTO {

    @Schema(name = "RestaurantRequestDTO")
    @Getter
    public static class Request {

        @Schema(description = "레스토랑 이름", example = "99피자", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "레스토랑 위치", example = "서울시 99로", requiredMode = Schema.RequiredMode.REQUIRED)
        private String location;

    }

    @Schema(name = "RestaurantResponseDTO")
    @Getter
    @Builder
    public static class Response {

        @Schema(description = "레스토랑 id", example = "1")
        private Long id;

        @Schema(description = "레스토랑 이름", example = "99피자")
        private String name;

        @Schema(description = "레스토랑 위치", example = "서울시 99로")
        private String location;
    }

    @Schema(name = "RestaurantDeleteResponseDTO")
    @Getter
    @Builder
    public static class DeleteResponse {

        @Schema(description = "삭제된 레스토랑 id", example = "1")
        private Long id;
        
    }
}
