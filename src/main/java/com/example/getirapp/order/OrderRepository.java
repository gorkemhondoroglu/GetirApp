package com.example.getirapp.order;

import com.example.getirapp.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order,Long> {

    List<Order> findAllByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByCustomerId(Long customerId);


}
