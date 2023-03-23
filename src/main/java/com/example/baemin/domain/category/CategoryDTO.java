package com.example.baemin.domain.category;

import lombok.Builder;
import lombok.Getter;

public class CategoryDTO {

    @Getter
    public static class Request {
        private String name;
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    public static class DeleteResponse {
        private Long id;
    }

}
