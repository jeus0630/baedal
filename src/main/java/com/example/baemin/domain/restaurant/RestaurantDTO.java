package com.example.baemin.domain.restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RestaurantDTO {

    @Getter
    public static class Request {
        private String name;
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

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String name;
        private String location;
    }

    @Getter
    @Builder
    public static class DeleteResponse {
        private Long id;
    }
}
