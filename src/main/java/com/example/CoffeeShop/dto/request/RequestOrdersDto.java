package com.example.CoffeeShop.dto.request;

import com.example.CoffeeShop.entity.Customer;
import com.example.CoffeeShop.entity.Orders;
import lombok.*;

import java.util.List;

/**
 * 주문 요청 보내기 위한 dto
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RequestOrdersDto {
    private Long customerId; //고객 id
    private List<DrinkQtyDto> drinksList; //주문 음료 list
    public Orders toEntity(Long ordersId,Customer customer){
        return Orders.of(
                ordersId,
                customer,
                drinksList,
                1
        );
    }
}
