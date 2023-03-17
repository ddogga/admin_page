package com.admin.shop3.service;

import com.admin.shop3.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChartService {

    private final OrderRepository orderRepository;

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
