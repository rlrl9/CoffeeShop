package com.example.CoffeeShop.entity;

import com.example.CoffeeShop.global.exception.CoffeeBusinessException;
import com.example.CoffeeShop.global.exception.CoffeeExceptionInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum PaymentMethod {
    CASH(0L, "현금"),
    CARD(1L, "카드"),
    GIFT(2L, "상품권"),
    ;

    @JsonValue
    private final Long value;
    private final String description;

    public static PaymentMethod ofCode(Long dbData) {
        return Arrays.stream(PaymentMethod.values())
                .filter(v -> Objects.equals(v.getValue(), dbData))
                .findAny()
                .orElseThrow(() -> new CoffeeBusinessException(CoffeeExceptionInfo.NOT_EXIST_METHOD));
    }
}
