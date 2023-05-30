package com.admin.shop3.repository;

import com.admin.shop3.dto.MonthItemCostSum;
import com.admin.shop3.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    public void 달별_순이익_조회() throws Exception{
        //given
        LocalDate now = LocalDate.now();

        LocalDate start = LocalDate.of(now.getYear()-1, now.getMonthValue()+1, 1);
        //when

        List<MonthItemCostSum> costSums = itemRepository.findGroupByItemWithJPQL(start,now);

        //then
        for (MonthItemCostSum costSum : costSums) {
            System.out.println(costSum.getOrderMonth());

            Long total = 0L;

            List<OrderItem> orderItems = orderItemRepository.findAllByMonth(costSum.getOrderMonth());

            for(OrderItem orderItem : orderItems) {
                total += orderItem.getCount() * orderItem.getItem().getItemCost();
            }
            System.out.println("costSum = " + costSum.getTotalProfit());
            System.out.println("total = " + total);

            assertEquals(costSum.getTotalProfit(), total);

        }
    }
}