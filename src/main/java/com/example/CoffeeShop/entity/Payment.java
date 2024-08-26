package com.example.CoffeeShop.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId; // 결제 id

    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders; // 주문 정보

    private boolean status; // 상태 (false: 결제 실패, true: 결제 성공)

    public static Payment of(Orders orders){
        Payment payment = new Payment();
        payment.orders = orders;
        payment.status = true;

        return payment;
    }
}
