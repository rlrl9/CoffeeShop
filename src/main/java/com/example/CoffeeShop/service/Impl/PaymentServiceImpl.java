package com.example.CoffeeShop.service.Impl;

import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.*;
import com.example.CoffeeShop.global.exception.CoffeeBusinessException;
import com.example.CoffeeShop.global.exception.CoffeeExceptionInfo;
import com.example.CoffeeShop.repository.OrdersRepository;
import com.example.CoffeeShop.repository.PaymentRepository;
import com.example.CoffeeShop.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final OrdersRepository ordersRepository;
    private final PaymentRepository paymentRepository;
    /**
     * 주문 결제
     * @param ordersId
     * @param paymentMethod
     * @return ResponsePaymentDto
     */
    @Override
    public ResponsePaymentDto pay(Long ordersId, String paymentMethod){
        Orders orders = ordersRepository.findByOrdersId(ordersId)
                .orElseThrow(() -> new CoffeeBusinessException(CoffeeExceptionInfo.NOT_EXIST_ORDERS));
        if(orders.getStatus() == OrderStatus.CANCELED.getValue()){
            throw new CoffeeBusinessException(CoffeeExceptionInfo.ALREADY_CANCELED);
        } else if (orders.getStatus() == OrderStatus.COMPLETED.getValue()) {
            throw new CoffeeBusinessException(CoffeeExceptionInfo.ALREADY_PAID);
        } else if (orders.getStatus() == OrderStatus.DELIVERED.getValue()) {
            throw new CoffeeBusinessException(CoffeeExceptionInfo.ALREADY_DELIVERED);
        }
        try {
            // 결제 처리
            Payment payment = paymentRepository.save(Payment.of(orders, PaymentMethod.of(paymentMethod)));
            orders.updateAfterPayment();//상태 '2'로 변경(주문 완료 상태)
            return ResponsePaymentDto.fromPayment(payment, PaymentStatus.PAID);
        } catch (Exception e) {
            return ResponsePaymentDto.fromOrders(orders,PaymentStatus.NOT_PAID);
        }
    }
}
