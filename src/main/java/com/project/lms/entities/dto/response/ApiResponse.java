package com.project.lms.entities.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse of(int status, String message, T data) {
        return ApiResponse.builder().status(status).message(message).data(data).build();
    }

    public static <T> ApiResponse ok(String message) {
        return ApiResponse.builder().status(200).message(message).build();
    }

    public static <T> ApiResponse ok(String message, T data) {
        return ApiResponse.builder().status(200).message(message).data(data).build();
    }
}
