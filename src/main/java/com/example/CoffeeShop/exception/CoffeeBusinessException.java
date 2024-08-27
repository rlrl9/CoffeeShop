package com.example.CoffeeShop.exception;

import com.example.CoffeeShop.global.exception.BusinessException;
import com.example.CoffeeShop.global.exception.ExceptionInfo;
import lombok.Getter;

@Getter
public class CoffeeBusinessException extends BusinessException {
    public CoffeeBusinessException(ExceptionInfo exceptionInfo){
        super(exceptionInfo);
    }
}
