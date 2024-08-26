package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByCustomer_CustomerId(Long customerId);

    Optional<Orders> findByCustomer_CustomerIdAndStatus(Long customerId,int status);

    @Query("SELECT MAX(o.id) FROM Orders o")
    Long findMaxId();
}
