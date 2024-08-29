package com.example.CoffeeShop.controller;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
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
    public ResponseEntity<ApiResponse<ResponseOrdersDto>> registerOrder(@RequestBody RequestOrdersDto requestOrdersDto) {
        ResponseOrdersDto roDto = coffeeService.registerOrder(requestOrdersDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(roDto));
    }
    //음료 결제 (주문 id, 결제수단으로 결제)
    @GetMapping
    public ResponseEntity<ApiResponse<ResponsePaymentDto>> pay(@RequestParam(name = "id") Long ordersId, @RequestParam(name = "type") String paymentMethod) {
        ResponsePaymentDto rpDto = coffeeService.pay(ordersId, paymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(rpDto));
    }
    //음료 테이크아웃 (주문 id로 테이크아웃)
    @GetMapping("/takeout")
    public ResponseEntity<ApiResponse<ResponseOrdersDto>> takeoutMenu(@RequestParam(name = "id") Long ordersId) {
        ResponseOrdersDto roDto = coffeeService.takeoutMenu(ordersId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(roDto));
    }
}
