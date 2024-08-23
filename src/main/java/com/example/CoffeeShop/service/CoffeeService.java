package com.example.CoffeeShop.service;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.entity.Orders;

import java.util.List;

public interface CoffeeService {
    Orders insertOrder(RequestOrdersDto requestOrdersDto);
}
