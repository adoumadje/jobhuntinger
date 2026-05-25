package com.jobhuntinger.common.exception;

import com.jobhuntinger.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGlobalException(Exception exception) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
