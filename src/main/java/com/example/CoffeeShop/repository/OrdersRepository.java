package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // 주문 id로 주문 정보 찾기
    Optional<Orders> findByOrdersId(Long ordersId);
}
