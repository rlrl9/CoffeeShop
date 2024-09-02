package com.example.CoffeeShop.global.exception;

import lombok.Getter;

@Getter
public enum CoffeeExceptionInfo{
    NOT_EXIST_DRINKS("NOT_EXIST_DRINKS","존재하지 않는 상품입니다."),
    NOT_EXIST_ORDERS("NOT_EXIST_ORDERS","해당 주문 건이 존재하지 않습니다."),
    NOT_EXIST_TAKEOUT("NOT_EXIST_TAKEOUT","테이크 아웃할 상품이 존재하지 않습니다."),
    NOT_EXIST_CUSTOMER("NOT_EXIST_CUSTOMER","해당 고객이 존재하지 않습니다."),
    NOT_EXIST_PAID("NOT_EXIST_PAID","결제를 먼저 진행해주세요."),
    NOT_EXIST_METHOD("NOT_EXIST_METHOD","존재하지 않는 결제 수단입니다."),
    ALREADY_DELIVERED("ALREADY_DELIVERED","이미 테이크아웃된 주문입니다."),
    ALREADY_CANCELED("ALREADY_CANCELED","취소된 주문입니다."),
    ALREADY_PAID("ALREADY_PAID","이미 결제된 주문입니다."),
    ;

    private final String code;
    private final String message;

    CoffeeExceptionInfo(String code, String message){
        this.code = code;
        this.message = message;
    }
}
