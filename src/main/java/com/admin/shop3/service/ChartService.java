package com.admin.shop3.service;

import com.admin.shop3.dto.MonthItemCostSum;
import com.admin.shop3.dto.MonthOrderSum;
import com.admin.shop3.repository.ItemRepository;
import com.admin.shop3.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ChartService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
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
        LocalDate now = LocalDate.now();
        LocalDate start = LocalDate.of(now.getYear()-1,now.getMonthValue()+1,1);



        log.info("chart start: " + start + " end: " + now);
        List<MonthItemCostSum> monthItemCostSums = itemRepository.findGroupByItemWithJPQL(start,now);
        List<MonthOrderSum> monthOrderSums = new ArrayList<>();

        int i = 0;
        if (monthItemCostSums.size() > 0) {
            monthOrderSums = orderRepository.findGroupByOrderWithJPQL(start, now);

            for (MonthOrderSum orderSum : monthOrderSums) {
                orderSum.setTotalProfit(monthItemCostSums.get(i).getTotalProfit());
                i++;
            }
        }
        return monthOrderSums;
    }




}
