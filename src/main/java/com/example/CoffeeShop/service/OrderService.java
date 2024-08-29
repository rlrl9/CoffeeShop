package com.example.CoffeeShop.service;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;

/**
 * 메뉴 주문 서비스 인터페이스
 */
public interface OrderService {
    //메뉴 주문
    ResponseOrdersDto registerOrder(RequestOrdersDto requestOrdersDto);
}
