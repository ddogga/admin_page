package com.admin.shop3.repository;

import com.admin.shop3.dto.MonthOrderSum;
import com.admin.shop3.service.ChartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest//스프링 부트를 사용해서 스프링 컨테이너안에서 Autowired작업등을 수행하기 위해 붙이는 어노테이션
@Transactional//테스트가 끝나면 RollBack
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 달별_매출_조회() throws Exception {
        //given

        LocalDate now = LocalDate.now();

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate start = LocalDate.of(year-1, month+1, 1);
        System.out.println("start = " + start);
        LocalDate end = LocalDate.of(year, month, day);

        //when

        List<MonthOrderSum> orderSums = orderRepository.findGroupByOrderWithJPQL(start,end);

        //then

        for (MonthOrderSum orderSum : orderSums) {

            System.out.println(orderSum.getOrderMonth());

            if (month < orderSum.getOrderMonth()){
                year -= 1;
            }
            LocalDate monthStart = LocalDate.of(year, orderSum.getOrderMonth(), 1);
            LocalDate monthEnd = LocalDate.of(year, orderSum.getOrderMonth(), monthStart.lengthOfMonth());

            System.out.println("monthStart = " + monthStart);
            System.out.println("monthEnd = " + monthEnd);

            final Long[] totalPrice = {0L};

            orderRepository.findAllByOrderDateBetween(monthStart,monthEnd)
                    .stream()
                    .forEach(order -> totalPrice[0] += order.getTotalPrice());

            assertEquals(orderSum.getTotalIncome(), totalPrice[0]);
        }


    }

}