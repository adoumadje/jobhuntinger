package com.jobhuntinger.common.dto;

public record ErrorResponseDto(int code, String message, StackTraceElement[] stackTrace) {
}
