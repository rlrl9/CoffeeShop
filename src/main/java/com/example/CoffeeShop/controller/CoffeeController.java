package com.example.CoffeeShop.controller;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
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
    public ResponseEntity<?> insertOrder(@RequestBody RequestOrdersDto requestOrdersDto) {
        return ResponseEntity.status(HttpStatus.OK).body(coffeeService.insertOrder(requestOrdersDto));
    }
    //음료 결제
    @GetMapping
    public ResponseEntity<?> payForOrder(@RequestParam(name = "id") Long customerId,@RequestParam(name = "type") int paymentType) {
        return ResponseEntity.status(HttpStatus.OK).body(coffeeService.payForOrder(customerId,paymentType));
    }
    //음료 테이크아웃
    @GetMapping("/takeout")
    public ResponseEntity<?> takeoutMenu(@RequestParam(name = "id") Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(coffeeService.takeoutMenu(customerId));
    }
}
