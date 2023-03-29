package com.admin.shop3.repository;

import com.admin.shop3.entity.Event;
import com.admin.shop3.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
