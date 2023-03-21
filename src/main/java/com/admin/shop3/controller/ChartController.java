package com.admin.shop3.controller;


import com.admin.shop3.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    /**
     * 이번달 매출 조회
     *
     */
    @GetMapping("/income/monthly")
    public Map<String, Object> getMonthly() {
        Map<String, Object> response = new HashMap<>();

        Long income = chartService.getIncomeMonthly();
        System.out.println(income);
        response.put("result", income);

        return response;
    }

    /**
     * 이번 년도 매출 조회
     * @return
     */

    @GetMapping("/income/annual")
    public Long getAnnual() {
        Long income = chartService.getIncomeAnnual();

        return income;
    }


    @GetMapping("/incomes/monthly")
    public ResponseEntity getIncomesMonthly() {
        return ResponseEntity.of(Optional.ofNullable(chartService.getIncomesMonthly()));
    }


}
