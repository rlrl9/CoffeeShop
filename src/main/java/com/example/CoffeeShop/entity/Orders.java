package com.example.CoffeeShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "orders_drinks_map", joinColumns = @JoinColumn(name = "orders_id"))
    @MapKeyColumn(name = "drinks_id")
    @Column(name = "qty")
    private Map<Long, Integer> drinksMap = new HashMap<>();

    //0:취소 1:대기 2:완료 3:배송완료
    private int status;

    public static Orders of(Customer customer, Map<Long,Integer> drinksMap, int status){
        return new Orders(null, customer, drinksMap, status);
    }
}
