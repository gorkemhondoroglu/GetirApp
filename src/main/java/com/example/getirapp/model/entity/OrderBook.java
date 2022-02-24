package com.example.getirapp.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Data
@Document
public class OrderBook {

    private Book book;

    private Integer count;
}
