package com.example.CoffeeShop.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    WAITING(1, "주문 대기중"),
    COMPLETED(2, "주문 완료"),
    CANCELED(0, "주문 취소"),
    DELIVERED(3, "테이크아웃 완료"),
    ;

    @JsonValue
    private final int value;
    private final String description;

    @JsonCreator
    public static OrderStatus of(String value) {
        return OrderStatus.valueOf(value);
    }
}
