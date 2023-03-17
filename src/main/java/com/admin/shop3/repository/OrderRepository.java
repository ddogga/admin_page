package com.admin.shop3.repository;

import com.admin.shop3.dto.MonthOrderSum;
import com.admin.shop3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {



    List<Order> findAllByOrderDateBetween(LocalDate start, LocalDate end);

    @Query(value =
            "SELECT "+
                    " new com.admin.shop3.dto.MonthOrderSum(month(or.orderDate),COUNT(or.id),SUM(or.totalPrice)) " +
                    "FROM Order or " +
                    "GROUP BY month(or.orderDate) " +
                    "HAVING or.orderDate BETWEEN :start AND :end"
    )
    List<MonthOrderSum> findGroupByOrderWithJPQL(LocalDate start, LocalDate end);
}
