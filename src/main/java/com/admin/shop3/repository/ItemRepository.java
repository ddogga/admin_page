package com.admin.shop3.repository;

import com.admin.shop3.dto.MonthItemCostSum;
import com.admin.shop3.entity.Item;
import com.admin.shop3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long id);

    List<Item> findAllTop10ByOrderBySalesQuantityDesc();

    List<Item> findAll();
    List<Item> findAllByOrderByItemStatusDesc();




    @Query(value =
            "SELECT "+
                    " new com.admin.shop3.dto.MonthItemCostSum(month(oi.orderDate), SUM(oi.item.itemCost * oi.count)) " +
                    "FROM OrderItem oi " +
                    "WHERE oi.order.status = 'FINISH' " +
                    "GROUP BY month(oi.orderDate) " +
                    "HAVING oi.orderDate BETWEEN :start AND :end"
    )
    List<MonthItemCostSum> findGroupByItemWithJPQL(LocalDate start, LocalDate end);
}
