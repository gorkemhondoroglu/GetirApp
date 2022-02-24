package com.example.getirapp.model.mapper;

import com.example.getirapp.model.dto.OrderDto;
import com.example.getirapp.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order convert(OrderDto request);

    OrderDto convert(Order book);

}
