package com.admin.shop3.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;


@Getter
@Setter
public class MonthOrderSum {

    private int orderMonth;
    private Long totalCount;
    private Long totalIncome;

    public MonthOrderSum(int orderMonth, Long totalCount, Long totalIncome) {
        this.orderMonth = orderMonth;
        this.totalCount = totalCount;
        this.totalIncome = totalIncome;
    }
}
