package com.example.baemin.domain.food;

import lombok.Builder;
import lombok.Getter;

public class FoodDto {

    @Getter
    public static class Request {
        private String name;
        private Integer price;
        private Long categoryId;
        private Long restaurantId;
    }

    @Getter
    @Builder
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
