package com.example.getirapp.book;

import com.example.getirapp.model.entity.Book;

import java.util.List;

public interface BookService {

    Book add(Book book);

    Book updateStock(Long bookId, Integer stock);

    List<Book> getAllBooks();
}
