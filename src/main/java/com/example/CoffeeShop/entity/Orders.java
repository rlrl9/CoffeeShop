package com.example.CoffeeShop.entity;

import com.example.CoffeeShop.dto.request.DrinkQtyDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Orders {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "orders_drinks_map", joinColumns = @JoinColumn(name = "orders_id"))
    @MapKeyColumn(name = "drinks_id")
    @Column(name = "qty")
    private Map<Long, Integer> drinksMap = new HashMap<>();

//    @OneToMany
//    @JoinTable(name = "orders_drinks_map", joinColumns = @JoinColumn(name = "orders_id"),
//            inverseJoinColumns = @JoinColumn(name = "drinks_id"))
//    @MapKeyJoinColumn(name = "drinks_id")
//    @Column(name = "qty")
//    private Map<Drinks, Integer> drinksMap = new HashMap<>();

    //0:취소 1:대기 2:완료 3:배송완료
    private int status;

    public static Orders of(Customer customer, List<DrinkQtyDto> drinksList, int status){
        Orders order = new Orders();
        order.customer = customer;
        order.status = status;

        for (DrinkQtyDto drinkQty : drinksList) {
            order.drinksMap.put(drinkQty.getDrinksId(), drinkQty.getQty());
        }

        return order;
    }
    public void update(List<DrinkQtyDto> drinksList){
        for (DrinkQtyDto drinkQty : drinksList) {
            drinksMap.put(drinkQty.getDrinksId(), drinkQty.getQty());
        }
    }
    public void updateAfterPayment(){
//        drinksMap.clear();
        status = 2;
    }
}
