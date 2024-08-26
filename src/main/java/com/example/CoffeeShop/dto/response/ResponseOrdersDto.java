package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.entity.Orders;
import lombok.*;

import java.util.Map;

/**
 * 주문 응답 받기 위한 dto
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ResponseOrdersDto {
    private Long customerId; // 고객 id

    private Map<Long, Integer> drinksMap; // 음료 목록

    private String statusMessage; // 상태 메세지

    public static ResponseOrdersDto from(Orders orders){
        return ResponseOrdersDto.builder()
                .customerId(orders.getCustomer().getCustomerId())
                .drinksMap(orders.getDrinksMap())
                .statusMessage("테이크 아웃 했습니다.")
                .build();
    }
}
