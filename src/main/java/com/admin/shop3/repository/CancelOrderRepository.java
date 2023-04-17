package com.admin.shop3.repository;

import com.admin.shop3.entity.CancelOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancelOrderRepository extends JpaRepository<CancelOrder, Long> {

    Page<CancelOrder> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
