package com.admin.shop3.repository;

import com.admin.shop3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findAllByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
}
