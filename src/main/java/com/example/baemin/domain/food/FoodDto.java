package com.example.baemin.domain.food;

import com.example.baemin.domain.category.Category;
import com.example.baemin.domain.restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class FoodDto {

    @Getter
    public static class Request {
        private String name;
        private Integer price;
        private Long categoryId;
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

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    @Builder
    public static class DeleteResponse {
        private Long id;
    }
}
