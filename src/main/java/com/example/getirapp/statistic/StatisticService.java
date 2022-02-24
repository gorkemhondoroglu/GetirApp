package com.example.getirapp.statistic;

import com.example.getirapp.model.entity.*;

import java.util.List;

public interface StatisticService {

    List<Statistic> getMonthlyStatistics(Long customerId);

}
