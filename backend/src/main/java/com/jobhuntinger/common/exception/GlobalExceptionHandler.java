package com.jobhuntinger.common.exception;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.common.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(Constants.STATUS_BAD_REQUEST, exception.getMessage(),
                exception.getStackTrace());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
