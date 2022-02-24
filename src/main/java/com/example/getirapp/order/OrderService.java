package com.example.getirapp.order;

import com.example.getirapp.model.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order getOrderByOrderId(Long orderId);

    List<Order> getByDateInterval(LocalDateTime startDate, LocalDateTime endDate);

    Order addOrder(Order order);

    List<Order> getAllOrdersByCustomerId(Long customerId);

}
