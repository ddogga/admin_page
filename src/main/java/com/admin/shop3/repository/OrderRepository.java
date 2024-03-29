package com.admin.shop3.repository;

import com.admin.shop3.dto.MonthOrderSum;
import com.admin.shop3.entity.Order;
import com.admin.shop3.entity.state.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {



    List<Order> findAllByOrderDateBetween(LocalDate start, LocalDate end);

    @Query(value =
            "SELECT "+
                    " new com.admin.shop3.dto.MonthOrderSum(or.orderMonth,COUNT(or.id),SUM(or.totalPrice)) " +
                    "FROM Order or " +
                    "WHERE or.status = 'FINISH' " +
                    "AND or.orderDate BETWEEN :start AND :end " +
                    "GROUP BY or.orderMonth "
    )
    List<MonthOrderSum> findGroupByOrderWithJPQL(LocalDate start, LocalDate end);



    Page<Order> findByStatusNotOrderByOrderTimeDesc(OrderStatus orderStatus, Pageable pageable);


    @Query(value =
        "SELECT or "+
                "from Order or " +
                "where or.status not in ('CANCEL','FINISH') " +
                "and or.orderDate = :date " +
                "order by or.orderTime desc"
    )
    List<Order> findCurrentOrdersWithJPQL(LocalDate date);

    Optional<Order> findByIdAndStatusNot(Long id, OrderStatus orderStatus);

}
