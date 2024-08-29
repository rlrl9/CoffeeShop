package com.example.CoffeeShop.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentStatus {
    NOT_PAID(0, "결제 실패"),
    PAID(1, "결제 완료"),
    ;

    @JsonValue
    private final int value;
    private final String description;

    @JsonCreator
    public static PaymentStatus of(String value) {
        return PaymentStatus.valueOf(value);
    }
}
