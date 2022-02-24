package com.example.getirapp.book;

import com.example.getirapp.model.dto.BookDto;
import com.example.getirapp.model.dto.BookStoreDto;
import com.example.getirapp.model.entity.Book;
import com.example.getirapp.model.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping(value = "/add")
    public ResponseEntity<Book> add(@RequestBody @Validated BookDto bookDto) {

        Book book = bookService.add(BookMapper.INSTANCE.convert(bookDto));
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/updateStock")
    public ResponseEntity<Book> update(@RequestBody @Validated BookStoreDto bookStoreDTO) {
        Book book = bookService.updateStock(bookStoreDTO.getId(), bookStoreDTO.getCount());
        return ResponseEntity.ok(book);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
