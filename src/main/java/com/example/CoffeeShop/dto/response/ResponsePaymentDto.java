package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.entity.OrdersDrinks;
import com.example.CoffeeShop.entity.Payment;
import lombok.*;

import java.util.List;

/**
 * 결제 응답 받기 위한 dto
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ResponsePaymentDto {

    private Long customerId; // 고객 id

    private List<OrdersDrinks> drinksMap; // 음료 list

    private boolean status; // 결제 성공/실패 상태 (false: 실패, true: 성공)

    private int paymentType; // 결제 수단(0: 현금, 1: 카드, 2: 상품권)

    private Long totPrice; // 총 금액

    public static ResponsePaymentDto from(Payment payment){
        return ResponsePaymentDto.builder()
                .customerId(payment.getOrders().getCustomer().getCustomerId())
                .drinksMap(payment.getOrders().getOrdersDrinksList())
                .status(payment.isStatus())
                .paymentType(payment.getPaymentType())
                .totPrice(payment.getOrders().getTotPrice())
                .build();
    }
}
