package com.example.CoffeeShop.entity;

import com.example.CoffeeShop.dto.request.DrinkQtyDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 주문 정보
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Orders {
    @Id
    private Long ordersId; // 주문 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer; // 고객 정보

    @ElementCollection
    @CollectionTable(name = "orders_drinks_map", joinColumns = @JoinColumn(name = "orders_id"))
    @MapKeyColumn(name = "drinks_id")
    @Column(name = "qty")
    private Map<Long, Integer> drinksMap = new HashMap<>(); // 주문 음료 리스트 map
    
    private int status; // 주문 상태 (0:취소 1:결제 대기 2:결제 완료 3:테이크아웃 완료)

    public static Orders of(Long ordersId, Customer customer, List<DrinkQtyDto> drinksList, int status){
        Orders order = new Orders();
        order.ordersId = ordersId;
        order.customer = customer;
        order.status = status;

        for (DrinkQtyDto drinkQty : drinksList) {
            order.drinksMap.put(drinkQty.getDrinksId(), drinkQty.getQty());
        }

        return order;
    }
    // 주문 음료 리스트 수정
    public void update(List<DrinkQtyDto> drinksList){
        for (DrinkQtyDto drinkQty : drinksList) {
            drinksMap.put(drinkQty.getDrinksId(), drinkQty.getQty());
        }
    }
    // 주문 완료 상태로 업데이트
    public void updateAfterPayment(){
        status = 2;
    }
    // 테이크아웃 완료 상태로 업데이트
    public void updateAfterTakeout(){
        status = 3;
    }
}
