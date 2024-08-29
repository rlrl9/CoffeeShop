package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.entity.OrdersDrinks;
import com.example.CoffeeShop.entity.Payment;
import com.example.CoffeeShop.entity.PaymentStatus;
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
    private Long ordersId; // 주문 id

    private Long customerId; // 고객 id

    private List<OrdersDrinks> drinksMap; // 음료 list

    private String paymentStatus; // 결제 성공/실패 상태

    private String paymentMethod; // 결제 수단(0: 현금, 1: 카드, 2: 상품권)

    private Long totPrice; // 총 금액

    // 결제 성공시
    public static ResponsePaymentDto fromPayment(Payment payment, PaymentStatus paymentStatus){
        return ResponsePaymentDto.builder()
                .ordersId(payment.getOrders().getOrdersId())
                .customerId(payment.getOrders().getCustomer().getCustomerId())
                .drinksMap(payment.getOrders().getOrdersDrinksList())
                .paymentStatus(paymentStatus.getDescription())
                .paymentMethod(payment.getPaymentMethod().getDescription())
                .totPrice(payment.getOrders().getTotPrice())
                .build();
    }

    //결제 실패시
    public static ResponsePaymentDto fromOrders(Orders orders, PaymentStatus paymentStatus){
        return ResponsePaymentDto.builder()
                .ordersId(orders.getOrdersId())
                .customerId(orders.getCustomer().getCustomerId())
                .drinksMap(orders.getOrdersDrinksList())
                .paymentStatus(paymentStatus.getDescription())
                .paymentMethod(null)
                .totPrice(0L)
                .build();
    }
}
