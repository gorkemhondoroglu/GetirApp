package com.example.getirapp.common.base;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<ENTITY, DTO> {

    DTO convertToDto(ENTITY entity);

    ENTITY convertToEntity(DTO dto);

    default List<DTO> convertEntityListToDtoList(List<ENTITY> entityList) {

        return entityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    default List<ENTITY> convertDtoListToEntityList(List<DTO> dtoList) {

        return dtoList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
