package com.admin.shop3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestApplication {

    public static void main(String[] args) {
//        LocalDateTime start = LocalDateTime.of(LocalDate.now().withMonth(1).withDayOfMonth(1), LocalTime.of(0,0,0));
//        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        LocalDate now = LocalDate.now();
        int day = now.getDayOfWeek().getValue();
        //이번주 일요일 날짜를 가져옴.
        LocalDate end = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth() + (7-day));

        System.out.println("start = " + now);
        System.out.println("end = " + end);

    }
}
