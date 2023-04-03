package com.example.baemin.domain.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RestaurantDTO {

    @Schema(name = "RestaurantRequestDTO")
    @Getter
    public static class Request {

        @Schema(description = "레스토랑 이름", example = "99피자", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "레스토랑 위치", example = "서울시 99로", requiredMode = Schema.RequiredMode.REQUIRED)
        private String location;

        public Restaurant toEntity() {
            return Restaurant.builder()
                    .name(name)
                    .location(location)
                    .isActive(true)
                    .build();
        }

        public Restaurant toEntity(Long id) {
            return Restaurant.builder()
                    .id(id)
                    .name(name)
                    .location(location)
                    .isActive(true)
                    .build();
        }
        
    }

    @Schema(name = "RestaurantResponseDTO")
    @Getter
    @Setter
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
