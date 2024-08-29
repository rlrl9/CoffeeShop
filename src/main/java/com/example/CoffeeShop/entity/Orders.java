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
//    private static final int CANCELED = 0; // 또는 enum
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId; // 주문 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer; // 고객 정보

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersDrinks> ordersDrinksList = new ArrayList<>(); //주문한 음료 목록

    @Column(name = "status",nullable = false)
    private int status; // 주문 상태

    private Long totPrice; // 총 주문 금액

    public static Orders of(Customer customer, int status, Long totPrice){
        Orders order = new Orders();
        order.customer = customer;
        order.status = status;
        order.totPrice = totPrice;

        return order;
    }
    // 주문의 최종가격 업데이트
    public void updateTotPrice(long price) {
        this.totPrice = price;
    }
    // 주문 완료 상태로 업데이트
    public void updateAfterPayment(){
        this.status = OrderStatus.COMPLETED.getValue();
    }
    // 테이크아웃 완료 상태로 업데이트
    public void updateAfterTakeout(){
        this.status = OrderStatus.DELIVERED.getValue();
    }
    // 주문 음료 추가하기
    public void addOrdersDrinks(OrdersDrinks ordersDrinks) {
        this.ordersDrinksList.add(ordersDrinks);
    }
}
