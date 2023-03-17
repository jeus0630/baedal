package com.example.baemin.domain.food.dto;

import lombok.Builder;
import lombok.Getter;

public class FoodDto {

    @Getter
    public static class FoodCreateRequest {
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    @Builder
    public static class FoodCreateResponse {
        private Long id;
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    @Builder
    public static class FoodReadResponse {
        private Long id;
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    public static class FoodUpdateRequest {
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    @Builder
    public static class FoodUpdateResponse {
        private Long id;
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    @Builder
    public static class FoodDeleteResponse {
        private Long id;
    }
}