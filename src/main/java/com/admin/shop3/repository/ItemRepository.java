package com.admin.shop3.repository;

import com.admin.shop3.dto.MonthItemCostSum;
import com.admin.shop3.entity.Item;
import com.admin.shop3.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long id);

    List<Item> findTop10ByOrderBySalesQuantityDesc();

    List<Item> findAll();

    List<Item> findAllByOrderByItemStatusDesc();

    Page<Item> findAllByOrderByIdDesc(Pageable pageable);




    @Query(value =

            "SELECT "+
                    "new com.admin.shop3.dto.MonthItemCostSum(oi.order.orderMonth, SUM(oi.item.itemCost * oi.count)) " +
                    "FROM OrderItem oi " +
                    "WHERE oi.order.status = 'FINISH' " +
                    "AND oi.order.orderDate BETWEEN :start AND :end " +
                    "GROUP BY oi.order.orderMonth "
    )
    List<MonthItemCostSum> findGroupByItemWithJPQL(LocalDate start, LocalDate end);
}
