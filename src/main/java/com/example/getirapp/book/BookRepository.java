package com.example.getirapp.book;

import com.example.getirapp.model.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    Book findByNameAndAuthor(String name, String author);

    Optional<Book> findById(Long id);

}
