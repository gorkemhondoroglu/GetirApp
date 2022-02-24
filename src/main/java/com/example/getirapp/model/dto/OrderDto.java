package com.example.getirapp.model.dto;


import com.example.getirapp.model.entity.Customer;
import com.example.getirapp.model.entity.OrderBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String status;

    private Customer customer;

    private List<OrderBook> books;

}
