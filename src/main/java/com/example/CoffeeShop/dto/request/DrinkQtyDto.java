package com.example.CoffeeShop.dto.request;

import com.example.CoffeeShop.entity.Drinks;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Setter
public class DrinkQtyDto {
    private Long drinksId;
    private Integer qty;
}
