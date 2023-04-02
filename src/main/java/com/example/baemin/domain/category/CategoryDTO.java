package com.example.baemin.domain.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class CategoryDTO {

    @Schema(name = "CategoryRequestDTO")
    @Getter
    public static class Request {

        @NotBlank(message = "은 필수 입력 값입니다.")
        @Schema(description = "카테고리 이름", example = "양식", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(name = "CategoryResponseDTO")
    @Getter
    @Builder
    public static class Response {

        @Schema(description = "카테고리 id", example = "1")
        private Long id;

        @Schema(description = "카테고리 이름", example = "양식")
        private String name;

    }

    @Schema(name = "CategoryDeleteResponseDTO")
    @Getter
    @Builder
    public static class DeleteResponse {

        @Schema(description = "삭제된 카테고리 id", example = "1")
        private Long id;

    }
}
