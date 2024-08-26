package com.example.CoffeeShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name="orders_id")
    private List<OrdersDrinks> ordersDrinksList = new ArrayList<>();

    public void addOrdersDrinks(OrdersDrinks ordersDrinks) {
        ordersDrinksList.add(ordersDrinks);
    }
    public void clearOrdersDrinks() {
        ordersDrinksList.clear();
    }
    private int status; // 주문 상태 (0:취소 1:결제 대기 2:결제 완료 3:테이크아웃 완료)

    public static Orders of(Long ordersId, Customer customer, int status){
        Orders order = new Orders();
        order.ordersId = ordersId;
        order.customer = customer;
        order.status = status;

        return order;
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
