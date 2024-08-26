package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.OrdersDrinks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDrinksRepository extends JpaRepository<OrdersDrinks, Long> {
}
