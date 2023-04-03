package com.example.baemin.domain.food;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.baemin.domain.category.Category;
import com.example.baemin.domain.restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

        public Food toEntity(Category category, Restaurant restaurant) {
            return Food.builder()
                    .name(name)
                    .price(price)
                    .category(category)
                    .restaurant(restaurant)
                    .isActive(true)
                    .build();
        }

        public Food toEntity(Long id, Category category, Restaurant restaurant) {
            return Food.builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .category(category)
                    .restaurant(restaurant)
                    .isActive(true)
                    .build();
        }
        
    }

    @Schema(name = "FoodResponseDTO")
    @Getter
    @Setter
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
