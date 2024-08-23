package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByCustomer_CustomerId(Long customerId);
}
