package com.example.CoffeeShop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String drinksName; // 음료 이름

    private Long price; // 음료 가격

    private int categoryNo; // 카테고리
}
