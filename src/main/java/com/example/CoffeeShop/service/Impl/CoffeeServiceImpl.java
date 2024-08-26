package com.example.CoffeeShop.service.Impl;

import com.example.CoffeeShop.dto.request.DrinkQtyDto;
import com.example.CoffeeShop.dto.request.RequestOrdersDto;
import com.example.CoffeeShop.dto.response.ResponseOrdersDto;
import com.example.CoffeeShop.dto.response.ResponsePaymentDto;
import com.example.CoffeeShop.entity.*;
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
    public Orders insertOrder(RequestOrdersDto requestOrdersDto){
        Customer customer = customerRepository.findByCustomerId(requestOrdersDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        Long totPrice = 0L;
        Orders orders;
        //주문해놓은 메뉴가 있을 경우
        if(ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).isPresent()){
            orders = ordersRepository.findByCustomer_CustomerIdAndStatus(requestOrdersDto.getCustomerId(),1).get();
            orders.clearOrdersDrinks();
        }else{ //주문해놓은 메뉴가 없을 경우
            //아예 orders 에 아무데이터도 없을 경우 1로 초기화
            Long ordersId = ordersRepository.findMaxId() != null ? ordersRepository.findMaxId() + 1 : 1L;
            orders = ordersRepository.save(requestOrdersDto.toEntity(ordersId,customer));
        }
        for (DrinkQtyDto drinkQty : requestOrdersDto.getDrinksList()) {
            Drinks drinks = drinksRepository.findByDrinksId(drinkQty.getDrinksId())
                    .orElseThrow(() -> new IllegalArgumentException("음료가 존재하지 않습니다 : " + drinkQty.getDrinksId()));
            OrdersDrinks ordersDrinks = OrdersDrinks.of(drinks, drinkQty.getQty());
            ordersDrinksRepository.save(ordersDrinks);
            orders.addOrdersDrinks(ordersDrinks);
            totPrice += drinkQty.getQty()*drinksRepository.findByDrinksId(drinkQty.getDrinksId()).get().getPrice();
            orders.setTotPrice(totPrice);
        }
        return orders;
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
        Orders orders = ordersRepository.findByCustomer_CustomerIdAndStatus(customerId,2)
                .orElseThrow(()->new IllegalArgumentException("해당 주문 건이 존재하지 않습니다."));
        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.from(orders);
        orders.updateAfterTakeout();//상태 '3'으로 변경(테이크아웃 완료 상태)
        return responseOrdersDto;
    }
}
