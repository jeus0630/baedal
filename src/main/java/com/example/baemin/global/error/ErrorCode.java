package com.example.baemin.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ;
    
    private HttpStatus httpStatus;
    private String message;

}
