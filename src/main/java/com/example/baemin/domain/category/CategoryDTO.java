package com.example.baemin.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CategoryDTO {

    @Getter
    public static class Request {
        private String name;

        public Category toEntity(Long id) {
            return Category.builder()
                    .id(id)
                    .name(name)
                    .build();
        }

        public Category toEntity() {
            return Category.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    @Setter
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
