package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.entity.Payment;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ResponseOrdersDto {
    private Long customerId;

    private Map<Long, Integer> drinksMap;

    private String statusMessage;

    public static ResponseOrdersDto from(Orders orders){
        return ResponseOrdersDto.builder()
                .customerId(orders.getCustomer().getCustomerId())
                .drinksMap(orders.getDrinksMap())
                .statusMessage("테이크 아웃 했습니다.")
                .build();
    }
}
