package com.example.CoffeeShop.controller;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.global.response.ApiResponse;
import com.example.CoffeeShop.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 메뉴 주문 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;
    //음료 주문
    @PostMapping
    public ResponseEntity<ApiResponse<?>> insertOrder(@RequestBody RequestOrdersDto requestOrdersDto) {
        Orders orders = coffeeService.insertOrder(requestOrdersDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(orders));
    }
    //음료 결제
    @GetMapping
    public ResponseEntity<ApiResponse<?>> payForOrder(@RequestParam(name = "id") Long customerId,@RequestParam(name = "type") int paymentType) {
        ResponsePaymentDto rpDto = coffeeService.payForOrder(customerId,paymentType);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(rpDto));
    }
    //음료 테이크아웃
    @GetMapping("/takeout")
    public ResponseEntity<ApiResponse<?>> takeoutMenu(@RequestParam(name = "id") Long customerId) {
        ResponseOrdersDto roDto = coffeeService.takeoutMenu(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(roDto));
    }
}
