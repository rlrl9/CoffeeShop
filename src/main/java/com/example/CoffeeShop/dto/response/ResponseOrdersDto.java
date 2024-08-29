package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.entity.OrderStatus;
import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.entity.OrdersDrinks;
import lombok.*;

import java.util.List;

/**
 * 주문 응답 받기 위한 dto
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ResponseOrdersDto {
    private Long customerId; // 고객 id

    private List<OrdersDrinks> drinksMap; // 음료 목록

    private String statusMessage; // 상태 메세지

    public static ResponseOrdersDto from(Orders orders, OrderStatus orderStatus){
        return ResponseOrdersDto.builder()
                .customerId(orders.getCustomer().getCustomerId())
                .drinksMap(orders.getOrdersDrinksList())
                .statusMessage(orderStatus.getDescription())
                .build();
    }
}
