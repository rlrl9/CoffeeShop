package com.example.CoffeeShop.dto.response;

import com.example.CoffeeShop.dto.request.DrinkQtyDto;
import com.example.CoffeeShop.entity.Customer;
import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.entity.Payment;
import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ResponsePaymentDto {

    private Long customerId;

    private Map<Long, Integer> drinksMap;

    private boolean status;

    public static ResponsePaymentDto from(Payment payment){
        return ResponsePaymentDto.builder()
                .customerId(payment.getOrders().getCustomer().getCustomerId())
                .drinksMap(payment.getOrders().getDrinksMap())
                .status(payment.isStatus())
                .build();
    }
}
