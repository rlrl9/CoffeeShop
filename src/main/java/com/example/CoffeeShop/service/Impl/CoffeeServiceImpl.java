package com.example.CoffeeShop.service.Impl;

import com.example.CoffeeShop.dto.request.DrinkQtyDto;
import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.*;
import com.example.CoffeeShop.global.exception.CoffeeBusinessException;
import com.example.CoffeeShop.global.exception.CoffeeExceptionInfo;
import com.example.CoffeeShop.repository.*;
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
    private final DrinksRepository drinksRepository;
    private final OrdersDrinksRepository ordersDrinksRepository;
    /**
     * 메뉴 주문
     * @param requestOrdersDto
     * @return Orders
     */
    @Override
    public ResponseOrdersDto registerOrder(RequestOrdersDto requestOrdersDto){
        Customer customer = customerRepository.findByCustomerId(requestOrdersDto.getCustomerId())
                .orElseThrow(() -> new CoffeeBusinessException(CoffeeExceptionInfo.NOT_EXIST_CUSTOMER));
        long totPrice=0L;
        Orders orders = ordersRepository.save(Orders.of(customer,OrderStatus.WAITING.getValue(), totPrice));
        for (DrinkQtyDto drinkQty : requestOrdersDto.getDrinksList()) {
            Drinks drinks = drinksRepository.findByDrinksId(drinkQty.getDrinksId())
                    .orElseThrow(() -> new CoffeeBusinessException(CoffeeExceptionInfo.NOT_EXIST_DRINKS));
            OrdersDrinks ordersDrinks = OrdersDrinks.of(drinks, drinkQty.getQty(),orders);
            ordersDrinksRepository.save(ordersDrinks);
            orders.addOrdersDrinks(ordersDrinks);
            totPrice += drinkQty.getQty() * drinks.getPrice();
        }
        orders.updateTotPrice(totPrice);
        return ResponseOrdersDto.from(orders,OrderStatus.WAITING);
    }

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
                return ResponsePaymentDto.fromPayment(payment,PaymentStatus.PAID);
            } catch (Exception e) {
                return ResponsePaymentDto.fromOrders(orders,PaymentStatus.NOT_PAID);
            }
        }
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
