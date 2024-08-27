package com.example.CoffeeShop.exception;

import com.example.CoffeeShop.global.exception.ExceptionInfo;
import lombok.Getter;

@Getter
public enum CoffeeExceptionInfo implements ExceptionInfo {
    NOT_EXIST_DRINKS("NOT_EXIST_DRINKS","존재하지 않는 상품입니다."),
    NOT_EXIST_ORDERS("NOT_EXIST_ORDERS","해당 주문 건이 존재하지 않습니다."),
    NOT_EXIST_TAKEOUT("NOT_EXIST_TAKEOUT","테이크 아웃할 상품이 존재하지 않습니다."),
    NOT_EXIST_CUSTOMER("NOT_EXIST_CUSTOMER","해당 고객이 존재하지 않습니다.");

    private final String code;
    private final String message;

    CoffeeExceptionInfo(String code, String message){
        this.code = code;
        this.message = message;
    }
}
