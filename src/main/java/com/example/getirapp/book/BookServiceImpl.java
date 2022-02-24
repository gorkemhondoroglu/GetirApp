package com.example.getirapp.book;


import com.example.getirapp.common.exception.ResourceNotFoundException;
import com.example.getirapp.common.service.SequenceGeneratorService;
import com.example.getirapp.model.entity.Book;
import com.example.getirapp.model.entity.Order;
import com.example.getirapp.model.entity.OrderBook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    @Transactional
    public Book add(Book book) {
        bookIsExist(book);
        book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateStock(Long bookId, Integer stock) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            throw new ResourceNotFoundException("Book not found. Book id: " + bookId);
        }
        book.get().setCount(stock);
        final Book updatedBook = bookRepository.save(book.get());

        return updatedBook;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<OrderBook> getAvailableBooks(Order order) {
        List<OrderBook> bookList = new ArrayList<>();
        for (OrderBook orderedBook : order.getBooks()) {
            final Optional<Book> availableBook = bookRepository.findById(orderedBook.getBook().getId());

            if (availableBook != null && checkStock(orderedBook, availableBook.get())) {
                availableBook.get().setCount(availableBook.get().getCount() - orderedBook.getCount());
                bookList.add(orderedBook);
            } else {
                throw new ResourceNotFoundException("There is no stock for the requested book");
            }

        }
        return bookList;
    }

    private boolean checkStock(OrderBook orderBook, Book book) {
        if (book.getCount() - orderBook.getCount() > -1) {
            return true;
        }
        return false;
    }


    private void bookIsExist(Book book) {
        Book bookExist = bookRepository.findByNameAndAuthor(book.getName(), book.getAuthor());
        if (Objects.nonNull(bookExist)) {
            throw new IllegalArgumentException("Book is already exist with the same name for the given author. Book name: " + book.getName());
        }
    }
}
