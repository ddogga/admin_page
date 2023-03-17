package com.admin.shop3.controller;


import com.admin.shop3.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/income/monthly")
    public Map<String, Object> getMonthly() {
        Map<String, Object> response = new HashMap<>();

        Long income = chartService.getIncomeMonthly();
        System.out.println(income);
        response.put("result", income);

        return response;
    }

    @GetMapping("/income/annual")
    public Long getAnnual() {
        Long income = chartService.getIncomeAnnual();

        return income;
    }
}
