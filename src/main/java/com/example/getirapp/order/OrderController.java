package com.example.getirapp.order;


import com.example.getirapp.model.dto.OrderDto;
import com.example.getirapp.model.entity.Order;
import com.example.getirapp.model.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping(value = "/getByDateInterval")
    public ResponseEntity<List<Order>> getByDateInterval(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        List<Order> orderList = orderService.getByDateInterval(startDate, endDate);
        return ResponseEntity.ok(orderList);
    }

    @GetMapping(value = "/getByOrderId")
    public ResponseEntity<Order> getById(@RequestParam Long id) {
        Order order = orderService.getOrderByOrderId(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid OrderDto addOrderDTO) {
        Order order = orderService.addOrder(OrderMapper.INSTANCE.convert(addOrderDTO));
        return ResponseEntity.ok(order);
    }
}
