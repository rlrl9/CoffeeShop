package com.example.CoffeeShop.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 음료 정보
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Drinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drinksId; // 음료 id

    @Column(name = "drinks_name", nullable = false)
    private String drinksName; // 음료 이름

    @Column(name = "price", nullable = false)
    private Long price; // 음료 가격

    @Column(name = "category_no", nullable = false)
    private int categoryNo; // 카테고리
}
