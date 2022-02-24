package com.example.getirapp.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Document
public class Book implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "book_sequence";

    @Id
    private Long id;

    private String name;

    private String author;

    private Integer count;

    private Double price;
}
