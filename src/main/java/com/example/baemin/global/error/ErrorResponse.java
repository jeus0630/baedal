package com.example.baemin.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private String statusCode;
    private String message;

    public static ErrorResponse of(String statusCode, String message) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }

    public static ErrorResponse of(String statusCode, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .message(createMessage(bindingResult))
                .build();
    }

    private static String createMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if (isFirst) isFirst = false;
            else sb.append(", ");

            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("]");
            sb.append(fieldError.getDefaultMessage());
        }

        return sb.toString();
    }
}
