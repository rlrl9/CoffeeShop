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


@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeServiceImpl implements CoffeeService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    /**
     * 메뉴 주문
     * @param requestOrdersDto
     * @return Orders
     */
    @Override
    public Orders insertOrder(RequestOrdersDto requestOrdersDto){
        Customer customer = customerRepository.findByCustomerId(requestOrdersDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        //주문해놓은 메뉴가 있을 경우
        if(ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).isPresent()){
            Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).get();
            orders.update(requestOrdersDto.getDrinksList());
        }else{ //주문해놓은 메뉴가 없을 경우
            Long ordersId;
            if(ordersRepository.findMaxId()==null){ // orders 테이블에 아무 것도 없을 경우
                ordersId = (long) 0; // 초기화
            }else{
                ordersId = ordersRepository.findMaxId();
            }
            ordersRepository.save(requestOrdersDto.toEntity(ordersId+1,customer));
        }

        return ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).get();
    }
    /**
     * 주문 결제
     * @param customerId
     * @return ResponsePaymentDto
     */
    @Override
    public ResponsePaymentDto payForOrder(Long customerId, int paymentType){
        Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(customerId,1)
                .orElseThrow(()->new IllegalArgumentException("해당 주문 건이 존재하지 않습니다."));
        Payment payment = paymentRepository.save(Payment.of(orders, paymentType));
        orders.updateAfterPayment();//상태 '2'로 변경(결제 완료 상태)
        return ResponsePaymentDto.from(payment);
    }
    /**
     * 결제 메뉴 테이크아웃
     * @param customerId
     * @return ResponsePaymentDto
     */
    @Override
    public ResponseOrdersDto takeoutMenu(Long customerId){
//        Payment payment = paymentRepository.findByOrders_Customer_CustomerId(customerId)
//                .orElseThrow(()->new IllegalArgumentException("해당 결제 건이 존재하지 않습니다."));
        Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(customerId,2)
                .orElseThrow(()->new IllegalArgumentException("해당 주문 건이 존재하지 않습니다."));
        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.from(orders);
        orders.updateAfterTakeout();//상태 '3'으로 변경(배송 완료 상태)
        return responseOrdersDto;
    }
}
