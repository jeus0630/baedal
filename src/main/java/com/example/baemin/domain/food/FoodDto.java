package com.example.baemin.domain.food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class FoodDto {

    @Schema(name = "FoodRequestDTO")
    @Getter
    public static class Request {

        @Schema(description = "음식 이름", example = "99피자", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "음식 가격", example = "9900", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer price;

        @Schema(description = "카테고리 id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long categoryId;

        @Schema(description = "레스토랑 id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long restaurantId;

    }

    @Schema(name = "FoodResponseDTO")
    @Getter
    @Builder
    public static class Response {

        @Schema(description = "음식 id", example = "1")
        private Long id;

        @Schema(description = "음식 이름", example = "99피자")
        private String name;

        @Schema(description = "음식 가격", example = "9900")
        private Integer price;

        @Schema(description = "카테고리 id", example = "1")
        private Long categoryId;

        @Schema(description = "레스토랑 id", example = "1")
        private Long restaurantId;

    }

    @Schema(name = "FoodDeleteResponseDTO")
    @Getter
    @Builder
    public static class DeleteResponse {

        @Schema(description = "삭제된 레스토랑 id", example = "1")
        private Long id;
        
    }
}
