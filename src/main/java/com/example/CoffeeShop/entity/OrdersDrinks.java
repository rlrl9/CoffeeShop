package com.example.CoffeeShop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * 주문 음료 정보
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrdersDrinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orders_id", nullable = false)
    @JsonIgnore
    private Orders orders; // 주문 정보

    @ManyToOne
    @JoinColumn(name = "drinks_id", nullable = false)
    private Drinks drinks;

    @Column(name = "qty", nullable = false)
    private int quantity;

    public static OrdersDrinks of(Drinks drinks, int quantity, Orders orders){
        OrdersDrinks ordersDrinks = new OrdersDrinks();
        ordersDrinks.drinks = drinks;
        ordersDrinks.quantity = quantity;
        ordersDrinks.orders = orders;

        return ordersDrinks;
    }
}
