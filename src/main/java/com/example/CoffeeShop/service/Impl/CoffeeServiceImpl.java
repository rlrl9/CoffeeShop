package com.example.CoffeeShop.service.Impl;

import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.Customer;
import com.example.CoffeeShop.entity.Orders;
import com.example.CoffeeShop.entity.Payment;
import com.example.CoffeeShop.repository.CustomerRepository;
import com.example.CoffeeShop.repository.OrdersRepository;
import com.example.CoffeeShop.repository.PaymentRepository;
import com.example.CoffeeShop.service.CoffeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeServiceImpl implements CoffeeService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Orders insertOrder(RequestOrdersDto requestOrdersDto){
        Customer customer = customerRepository.findByCustomerId(requestOrdersDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        if(ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).isPresent()){
            Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).get();
            orders.update(requestOrdersDto.getDrinksList());
        }else{
            Long ordersId = ordersRepository.findMaxId();
            Orders orders = ordersRepository.save(requestOrdersDto.toEntity(ordersId+1,customer));
        }

        return ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).get();
    }

    @Override
    public ResponsePaymentDto payForOrder(Long customerId){
        Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(customerId,1)
                .orElseThrow(()->new IllegalArgumentException("해당 주문 건이 존재하지 않습니다."));
        Payment payment = paymentRepository.save(Payment.of(orders));
        orders.updateAfterPayment();
        return ResponsePaymentDto.from(payment);
    }

    @Override
    public ResponseOrdersDto takeoutMenu(Long customerId){
//        Payment payment = paymentRepository.findByOrders_Customer_CustomerId(customerId)
//                .orElseThrow(()->new IllegalArgumentException("해당 결제 건이 존재하지 않습니다."));
        Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(customerId,2)
                .orElseThrow(()->new IllegalArgumentException("해당 주문 건이 존재하지 않습니다."));
        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.from(orders);
        orders.updateAfterTakeout();
        return responseOrdersDto;
    }
}
