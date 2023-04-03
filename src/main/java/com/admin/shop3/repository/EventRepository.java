package com.admin.shop3.repository;

import com.admin.shop3.entity.Event;
import com.admin.shop3.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query(value =
        "SELECT ev "+
                "FROM Event ev " +
                "WHERE ( SUBSTRING(ev.startDate,1,10) BETWEEN :startDate AND :endDate OR " +
                "        SUBSTRING(ev.endDate,1,10) BETWEEN :startDate AND :endDate) " +
                "AND ev.userName = :userName"
    )
    List<Event> findEventBetweenDateWithJPQL(String startDate,String endDate, String userName);



}
