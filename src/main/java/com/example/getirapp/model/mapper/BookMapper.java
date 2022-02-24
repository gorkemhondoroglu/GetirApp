package com.example.getirapp.model.mapper;


import com.example.getirapp.model.dto.BookDto;
import com.example.getirapp.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper{

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book convert(BookDto request);

    BookDto convert(Book book);

}
