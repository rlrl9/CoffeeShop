package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.entity.Payment;
import lombok.*;

import java.util.Map;

/**
 * 결제 응답 받기 위한 dto
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ResponsePaymentDto {

    private Long customerId; // 고객 id

    private Map<Long, Integer> drinksMap; // 음료 list

    private boolean status; // 결제 성공/실패 상태 (false: 실패, true: 성공)

    public static ResponsePaymentDto from(Payment payment){
        return ResponsePaymentDto.builder()
                .customerId(payment.getOrders().getCustomer().getCustomerId())
                .drinksMap(payment.getOrders().getDrinksMap())
                .status(payment.isStatus())
                .build();
    }
}
