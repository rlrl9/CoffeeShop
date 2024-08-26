package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinksRepository extends JpaRepository<Drinks, Long> {
    // 음료 id로 음료 찾기
    Optional<Drinks> findByDrinksId(Long DrinksId);
}
