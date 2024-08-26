package com.example.CoffeeShop.entity;

import com.example.CoffeeShop.dto.request.DrinkQtyDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private boolean status;

    public static Payment of(Orders orders){
        Payment payment = new Payment();
        payment.orders = orders;
        payment.status = true;

        return payment;
    }
}
