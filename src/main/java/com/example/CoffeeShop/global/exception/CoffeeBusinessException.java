package com.example.CoffeeShop.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CoffeeBusinessException extends RuntimeException{
    private final CoffeeExceptionInfo coffeeExceptionInfo;
    @Builder
    public CoffeeBusinessException(CoffeeExceptionInfo coffeeExceptionInfo) {
        super(coffeeExceptionInfo.getMessage());
        this.coffeeExceptionInfo = coffeeExceptionInfo;
    }
}
