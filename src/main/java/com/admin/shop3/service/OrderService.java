package com.admin.shop3.service;


import com.admin.shop3.dto.*;
import com.admin.shop3.entity.*;
import com.admin.shop3.entity.state.OrderStatus;
import com.admin.shop3.exception.OrderNotFountException;
import com.admin.shop3.exception.UserNotFountException;
import com.admin.shop3.repository.CancelOrderRepository;
import com.admin.shop3.repository.ItemRepository;
import com.admin.shop3.repository.OrderRepository;
import com.admin.shop3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private final CancelOrderRepository cancelOrderRepository;

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


    public Page<OrderDto> getOrders(Pageable pageable) {
        return orderRepository.findByStatusNotOrderByOrderTimeDesc(OrderStatus.CANCEL,pageable)
                .map(Order::toDto);
    }

    public List<OrderItemDto> getOrderItemDtoList(Long id) {
        Order findOrder =  getOrderById(id);

        return findOrder.getOrderItemDtoList();
    }

    @Transactional
    public String updateStatus(OrderStatusUpdateReqDto dto) {
        Order findOrder = getOrderById(dto.getOrderId());

        findOrder.updateStatus(dto.getStatus());
        return "주문 상태 변경";
    }

    @Transactional
    public String cancelOrder(OrderCancelReqDto dto) {
        OrderStatusUpdateReqDto statusDto = createStatusDto(dto);
        updateStatus(statusDto);
        CancelOrder cancelOrder = createCancelOrder(dto);
        cancelOrderRepository.save(cancelOrder);

        return "주문 취소 완료";
    }

    private OrderStatusUpdateReqDto createStatusDto(OrderCancelReqDto dto) {
        return OrderStatusUpdateReqDto.builder()
                .orderId(dto.getOrderId())
                .status(dto.getStatus())
                .build();
    }

    private CancelOrder createCancelOrder(OrderCancelReqDto dto) {
        Order findOrder = getOrderById(dto.getOrderId());
        return CancelOrder.createCancelOrder(findOrder,dto.getReason());
    }

    private Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFountException::new);
    }

    public Page<CancelOrderDto> getCancelOrders(Pageable pageable) {
        return cancelOrderRepository.findAllByOrderByCreatedDateDesc(pageable)
                .map(CancelOrder::toDto);

    }

    @Transactional
    public String updateCancelOrder(CancelOrderUpdateReqDto dto) {
        CancelOrder findCancelOrder = cancelOrderRepository.findById(dto.getId())
                .orElseThrow(OrderNotFountException::new);

        findCancelOrder.update(dto.getReason());
        return "주문취소 사유 변경";
    }


    public List<OrderDto> getCurrentOrders() {
        LocalDate now = LocalDate.now();
        return orderRepository.findCurrentOrdersWithJPQL(now)
                .stream()
                .map(Order::toDto)
                .toList();
    }
}
