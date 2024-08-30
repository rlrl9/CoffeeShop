package com.example.CoffeeShop.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 고객 정보
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId; // 고객 id

    @Column(name = "name", nullable = false)
    private String name; // 고객 이름

    private String phoneNo; // 고객 전화 번호
}
