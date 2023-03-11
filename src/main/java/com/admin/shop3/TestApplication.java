package com.admin.shop3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestApplication {

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(LocalDate.now().withMonth(1).withDayOfMonth(1), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        System.out.println("start = " + start);
        System.out.println("end = " + end);

    }
}
