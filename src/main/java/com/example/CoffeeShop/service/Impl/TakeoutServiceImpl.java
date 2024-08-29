package com.example.CoffeeShop.service.Impl;

import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.entity.OrderStatus;
import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.global.exception.CoffeeBusinessException;
import com.example.CoffeeShop.global.exception.CoffeeExceptionInfo;
import com.example.CoffeeShop.repository.OrdersRepository;
import com.example.CoffeeShop.repository.PaymentRepository;
import com.example.CoffeeShop.service.TakeoutService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TakeoutServiceImpl implements TakeoutService {
    private final OrdersRepository ordersRepository;
    /**
     * 결제 메뉴 테이크아웃
     * @param ordersId
     * @return ResponsePaymentDto
     */
    @Override
    public ResponseOrdersDto takeoutMenu(Long ordersId){
        Orders orders = ordersRepository.findByOrdersId(ordersId)
                .orElseThrow(()->new CoffeeBusinessException(CoffeeExceptionInfo.NOT_EXIST_TAKEOUT));
        if(orders.getStatus() == OrderStatus.CANCELED.getValue()||orders.getStatus()==OrderStatus.WAITING.getValue()){
            throw new CoffeeBusinessException(CoffeeExceptionInfo.NOT_EXIST_PAID);
        } else if (orders.getStatus() == OrderStatus.DELIVERED.getValue()) {
            throw new CoffeeBusinessException(CoffeeExceptionInfo.ALREADY_DELIVERED);
        }
        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.from(orders, OrderStatus.DELIVERED);
        orders.updateAfterTakeout();
        return responseOrdersDto;
    }
}
