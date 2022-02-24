package com.example.getirapp.statistic;

import com.example.getirapp.customer.CustomerServiceImpl;
import com.example.getirapp.model.entity.*;
import com.example.getirapp.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Override
    public List<Statistic> getMonthlyStatistics(Long customerId) {
        List<Statistic> statisticList = new ArrayList<>();
        List<Order> orderList = orderService.getAllOrdersByCustomerId(customerId);
        String createdMonth = orderList.get(0).getCreateDate().getMonth().toString();
        Map<String, List<Order>> monthOrderListMap = orderList.stream().collect(Collectors.groupingBy(order -> {
            LocalDate localDate = order.getCreateDate();
            return localDate.getMonth().toString();
        }));

        return getStatisticDetails(monthOrderListMap, customerId);
    }

    private List<Statistic> getStatisticDetails(Map<String, List<Order>> monthOrderListMap,Long customerId) {
        List<Statistic> statisticList = new ArrayList<>();
        Statistic statistic = new Statistic();
        Customer customer = customerService.getCustomerById(customerId);
        monthOrderListMap.forEach((month, orders) -> {
            Long totalOrderCount = Long.valueOf(orders.size());
            Integer totalBookCount = 0;
            Double totalPurchasedAmount = 0.0;
            for (Order order : orders) {
                totalBookCount += order.getBooks().stream().map(OrderBook::getCount).reduce(0, Integer::sum);;
                totalPurchasedAmount += order.getTotalPrice();
            }

            StatisticDetails statisticDetail = new StatisticDetails(totalOrderCount, totalPurchasedAmount,totalBookCount);
            statistic.setStatisticDetails(statisticDetail);
            statistic.setMonth(month);
            statistic.setCustomer(customer);
            statisticList.add(statistic);
        });
        return statisticList;
    }
}
