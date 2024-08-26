package com.example.CoffeeShop.dto.request;

import com.example.CoffeeShop.entity.Customer;
import com.example.CoffeeShop.entity.Orders;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RequestOrdersDto {
    private Long customerId;
    private List<DrinkQtyDto> drinksList;
//    private Map<Long, Integer> drinksList;
    public Orders toEntity(Long ordersId,Customer customer){
        return Orders.of(
                ordersId,
                customer,
                drinksList,
                1
        );
    }
}
