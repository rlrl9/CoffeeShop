package com.example.CoffeeShop.repository;

import com.example.CoffeeShop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //고객 id로 고객 찾기
    Optional<Customer> findByCustomerId(Long customerId);
}
