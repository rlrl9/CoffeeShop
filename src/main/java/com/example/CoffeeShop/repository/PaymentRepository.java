package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrders_OrdersId(Long ordersId);
}
