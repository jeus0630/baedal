package com.example.baemin.domain.restaurant;

import lombok.Builder;
import lombok.Getter;

public class RestaurantDTO {

    @Getter
    public static class RestaurantCreateRequest {
        private String name;
        private String location;
    }

    @Getter
    @Builder
    public static class RestaurantCreateResponse {
        private Long id;
        private String name;
        private String location;
    }

    @Getter
    @Builder
    public static class RestaurantReadResponse {
        private Long id;
        private String name;
        private String location;
    }

    @Getter
    public static class RestaurantUpdateRequest {
        private String name;
        private String location;
    }

    @Getter
    @Builder
    public static class RestaurantUpdateResponse {
        private Long id;
        private String name;
        private String location;
    }

    @Getter
    @Builder
    public static class RestaurantDeleteResponse {
        private Long id;
    }
}
