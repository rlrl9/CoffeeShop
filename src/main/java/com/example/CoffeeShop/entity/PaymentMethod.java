package com.example.CoffeeShop.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentMethod {
    CASH(0, "현금"),
    CARD(1, "카드"),
    GIFT(2, "상품권"),
    ;

    @JsonValue
    private final int value;
    private final String description;

    @JsonCreator
    public static PaymentMethod of(String value) {
        return PaymentMethod.valueOf(value);
    }
}
