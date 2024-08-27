package com.example.CoffeeShop.global.response;

import com.example.CoffeeShop.global.exception.CoffeeExceptionInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse <T>{
    private T data;
    private String code;
    private String message;

    public static <T> ApiResponse<T> successResponse(T data) {
        return new ApiResponse<>(data, null, null);
    }

    public static ApiResponse<?> successWithNoContent() {
        return new ApiResponse<>(null, null, null);
    }

    public static ApiResponse<?> errorResponse(CoffeeExceptionInfo coffeeExceptionInfo){
        return new ApiResponse<>(null, coffeeExceptionInfo.getCode(), coffeeExceptionInfo.getMessage());
    }

    public static ApiResponse<?> errorResponse(String errorMessage){
        return new ApiResponse<>(null, null, errorMessage);
    }
}
