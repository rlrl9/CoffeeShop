package com.example.CoffeeShop.dto.request;

import com.example.CoffeeShop.entity.Customer;
import com.example.CoffeeShop.entity.Orders;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RequestOrdersDto {
    private Long customerId;
    private Map<Long, Integer> drinksList;

    public Orders toEntity(Customer customer){
        return Orders.of(
                customer,
                drinksList,
                1
        );
    }
//    public Order toEntity(){
//        return Order.of(
//                null,
//                0
//        );
//    }
}
