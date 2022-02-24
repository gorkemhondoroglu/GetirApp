package com.example.getirapp.statistic;

import com.example.getirapp.model.entity.Statistic;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class StatisticController {

    private final StatisticService statisticsService;

    @GetMapping(value = "/getMonthlyStatistics")
    public ResponseEntity<List<Statistic>> getMonthlyStatistics(@RequestParam Long customerId) {
        List<Statistic> statisticDetailList = statisticsService.getMonthlyStatistics(customerId);
        return ResponseEntity.ok(statisticDetailList);
    }
}
