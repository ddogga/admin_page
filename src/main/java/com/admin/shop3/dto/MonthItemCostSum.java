package com.admin.shop3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthItemCostSum {


    private int orderMonth;
    private Long totalProfit;


    public MonthItemCostSum(int orderMonth, Long totalProfit) {
        this.orderMonth = orderMonth;
        this.totalProfit = totalProfit;
    }
}
