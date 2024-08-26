package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // 고객 id, 상태로 주문 정보 찾기
    Optional<Orders> findByCustomer_CustomerIdAndStatus(Long customerId,int status);
    //최대 주문 id 찾기
    @Query("SELECT MAX(o.id) FROM Orders o")
    Long findMaxId();
}
