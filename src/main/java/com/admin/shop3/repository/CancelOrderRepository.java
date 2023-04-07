package com.admin.shop3.repository;

import com.admin.shop3.entity.CancelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelOrderRepository extends JpaRepository<CancelOrder, Long> {

}
