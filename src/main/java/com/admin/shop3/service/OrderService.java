package com.admin.shop3.service;


import com.admin.shop3.dto.OrderForm;
import com.admin.shop3.dto.OrderItemForm;
import com.admin.shop3.entity.Item;
import com.admin.shop3.entity.Order;
import com.admin.shop3.entity.OrderItem;
import com.admin.shop3.entity.User;
import com.admin.shop3.exception.UserNotFountException;
import com.admin.shop3.repository.ItemRepository;
import com.admin.shop3.repository.OrderRepository;
import com.admin.shop3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     * */

    @Transactional
    public String order(OrderForm orderForm) {

        //엔티티 조회
        User user = userRepository.findById(orderForm.getUserId())
                .orElseThrow(UserNotFountException::new);

        //주문 상품 생성
        List<OrderItem> orderItems = getOrderItems(orderForm.getOrderedItems());

        //주문 생성
        Order order = Order.createOrder(user, orderItems,getTotalPrice(orderItems));

        orderRepository.save(order);

        return "주문 완료";
    }

    private Item getItem(Long itemId){

        return itemRepository.findItemById(itemId);
    }

    private List<OrderItem> getOrderItems(List<OrderItemForm> orderItemList){
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemForm orderItem : orderItemList) {
            Item item = getItem(orderItem.getItemId());
            OrderItem newOrderItem = OrderItem.createOrderItem(item,item.getPrice(),orderItem.getCount());
            orderItems.add(newOrderItem);
        }
        return orderItems;
    }

    private int getTotalPrice(List<OrderItem> orderItemList){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItemList) {

            totalPrice += orderItem.getOrderItemPrice() * orderItem.getCount();
        }
        return totalPrice;
    }

    public Long getIncomeMonthly(){
        LocalDateTime start = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        final Long[] totalPrice = {0L};

        orderRepository.findAllByOrderTimeBetween(start,end)
                .stream()
                .forEach(order -> totalPrice[0] += order.getTotalPrice());


        return totalPrice[0];
    }

    public Long getIncomeAnnual(){
        LocalDateTime start = LocalDateTime.of(LocalDate.now().withMonth(1).withDayOfMonth(1), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        final Long[] totalPrice = {0L};

        orderRepository.findAllByOrderTimeBetween(start,end)
                .stream()
                .forEach(order -> totalPrice[0] += order.getTotalPrice());


        return totalPrice[0];
    }
}
