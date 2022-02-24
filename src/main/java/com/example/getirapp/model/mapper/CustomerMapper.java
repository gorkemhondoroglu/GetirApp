package com.example.getirapp.model.mapper;

import com.example.getirapp.model.dto.CustomerDto;
import com.example.getirapp.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper{

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer convert(CustomerDto request);

    CustomerDto convert(Customer book);
}
