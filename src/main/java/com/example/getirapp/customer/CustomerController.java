package com.example.getirapp.customer;


import com.example.getirapp.model.dto.CustomerDto;
import com.example.getirapp.model.entity.Customer;
import com.example.getirapp.model.entity.Order;
import com.example.getirapp.model.mapper.CustomerMapper;
import com.example.getirapp.order.OrderService;
import com.example.getirapp.order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public ResponseEntity<Customer> add(@RequestBody @Valid CustomerDto customer) {
        Customer newCustomer = customerService.add(CustomerMapper.INSTANCE.convert(customer));
        return ResponseEntity.ok(newCustomer);
    }

    @GetMapping(value = "/getAllOrdersByCustomerId")
    public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@RequestParam Long id) {
        List<Order> orderPage = orderService.getAllOrdersByCustomerId(id);
        return ResponseEntity.ok(orderPage);
    }
}
