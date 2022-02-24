package com.example.getirapp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Document
public class Customer implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "customer_sequence";

    @Id
    private Long id;

    @NotNull(message = "Customer name is required.")
    private String name;

    @NotNull(message = "Customer surname is required.")
    private String surname;

    @NotNull(message = "Email is required.")
    private String mail;
}
