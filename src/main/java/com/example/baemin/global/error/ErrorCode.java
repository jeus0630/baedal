package com.example.baemin.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 카테고리가 없습니다");

    private HttpStatus httpStatus;
    private String message;

}
