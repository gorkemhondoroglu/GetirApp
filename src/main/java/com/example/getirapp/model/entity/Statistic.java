package com.example.getirapp.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic implements Serializable {


    private StatisticDetails statisticDetails;

    private Customer customer;

    private String month;
}
