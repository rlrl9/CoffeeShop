package com.example.CoffeeShop.service;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.Orders;

/**
 * 메뉴 주문 서비스 인터페이스
 */
public interface CoffeeService {
    //메뉴 주문
    Orders insertOrder(RequestOrdersDto requestOrdersDto);
    //메뉴 결제
    ResponsePaymentDto payForOrder(Long customerId);
    //테이크아웃
    ResponseOrdersDto takeoutMenu(Long customerId);
}
