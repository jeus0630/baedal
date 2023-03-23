package com.example.baemin.domain.restaurant;

import lombok.Builder;
import lombok.Getter;

public class RestaurantDTO {

    @Getter
    public static class Request {
        private String name;
        private String location;
    }

    @Getter
    @Builder
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
