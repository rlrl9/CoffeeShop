package com.example.CoffeeShop.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * 주문 음료 정보
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class OrdersDrinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odId;

    @ManyToOne
    @JoinColumn(name = "drinks_id", nullable = false)
    private Drinks drinks;

    @Column(name = "qty", nullable = false)
    private Integer quantity;

    public static OrdersDrinks of(Drinks drinks, Integer quantity){
        OrdersDrinks ordersDrinks = new OrdersDrinks();
        ordersDrinks.drinks = drinks;
        ordersDrinks.quantity = quantity;

        return ordersDrinks;
    }
}
