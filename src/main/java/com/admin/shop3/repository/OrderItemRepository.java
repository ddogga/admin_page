package com.admin.shop3.repository;


import com.admin.shop3.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value =
        "SELECT oi "+
                "FROM OrderItem oi " +
                "WHERE month(oi.orderDate) = :month"
    )
    List<OrderItem> findAllByMonth(int month);
}
