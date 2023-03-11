package com.admin.shop3.repository;

import com.admin.shop3.entity.Item;
import com.admin.shop3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long id);

    List<Item> findAllByOrderBySalesQuantityDesc();

    List<Item> findAll();
}
