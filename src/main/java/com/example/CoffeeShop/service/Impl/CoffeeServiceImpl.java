package com.example.CoffeeShop.service.Impl;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.entity.Customer;
import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.repository.CustomerRepository;
import com.example.CoffeeShop.repository.OrdersRepository;
import com.example.CoffeeShop.service.CoffeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeServiceImpl implements CoffeeService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Orders insertOrder(RequestOrdersDto requestOrdersDto){
        Customer customer = customerRepository.findByCustomerId(requestOrdersDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        Orders orders = ordersRepository.save(requestOrdersDto.toEntity(customer));
        return ordersRepository.findByCustomer_CustomerId(requestOrdersDto.getCustomerId()).orElse(null);
    }
}
