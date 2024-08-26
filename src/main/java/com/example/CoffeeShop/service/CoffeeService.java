package com.example.CoffeeShop.service;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.Orders;

import java.util.List;

public interface CoffeeService {
    Orders insertOrder(RequestOrdersDto requestOrdersDto);
    ResponsePaymentDto payForOrder(Long customerId);
    ResponseOrdersDto takeoutMenu(Long customerId);
}
