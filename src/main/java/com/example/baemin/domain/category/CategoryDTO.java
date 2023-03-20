package com.example.baemin.domain.category;

import lombok.Builder;
import lombok.Getter;

public class CategoryDTO {

    @Getter
    public static class CategoryCreateRequest {
        private String name;
    }

    @Getter
    @Builder
    public static class CategoryCreateResponse {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    public static class CategoryReadResponse {
        private Long id;
        private String name;
    }

    @Getter
    public static class CategoryUpdateRequest {
        private String name;
    }

    @Getter
    @Builder
    public static class CategoryUpdateResponse {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    public static class CategoryDeleteResponse {
        private Long id;
    }
}
