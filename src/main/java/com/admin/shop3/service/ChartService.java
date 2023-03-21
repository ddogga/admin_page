package com.admin.shop3.service;

import com.admin.shop3.dto.MonthOrderSum;
import com.admin.shop3.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChartService {

    private final OrderRepository orderRepository;

    public Long getIncomeMonthly(){
        LocalDate now = LocalDate.now();
        LocalDate start = LocalDate.of(now.getYear(),now.getMonth(),1);
        LocalDate end = now;

        final Long[] totalPrice = {0L};

        orderRepository.findAllByOrderDateBetween(start,end)
                .stream()
                .forEach(order -> totalPrice[0] += order.getTotalPrice());

        return totalPrice[0];
    }

    public Long getIncomeAnnual(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate start = LocalDate.of(now.getYear(),1,1);
        LocalDate end = LocalDate.of(now.getYear(), 12,31);

        final Long[] totalPrice = {0L};

        orderRepository.findAllByOrderDateBetween(start,end)
                .stream()
                .forEach(order -> totalPrice[0] += order.getTotalPrice());

        return totalPrice[0];
    }

    public List<MonthOrderSum> getIncomesMonthly() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate start = LocalDate.of(now.getYear()-1,now.getMonthValue()+1,1);
        LocalDate end = LocalDate.of(now.getYear(), now.getMonthValue(),now.getDayOfMonth());

        return orderRepository.findGroupByOrderWithJPQL(start,end);
    }


}
