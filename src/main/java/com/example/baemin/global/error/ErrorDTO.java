package com.example.baemin.global.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class ErrorDTO {

    @Getter
    public static class CategoryNotFound {

        @Schema(description = "해당 상태 코드", example = "404 NOT_FOUND")
        private String errorCode;

        @Schema(description = "메세지", example = "일치하는 카테고리가 없습니다")
        private String message;

    }
}
