package com.example.getirapp.book;


import com.example.getirapp.common.service.SequenceGeneratorService;
import com.example.getirapp.model.entity.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Before
    public void setup() {
        bookService = new BookServiceImpl(bookRepository,sequenceGeneratorService);
        Mockito.when(bookRepository.findByNameAndAuthor("Test","Testets")).thenReturn(null);
        Mockito.when(sequenceGeneratorService.generateSequence("book_sequence")).thenReturn(1L);

    }

    @Test
    public void test_add() {
        Mockito.when(bookRepository.save(prepareBook())).thenReturn(prepareBook());
        final Book book = bookService.add(
                prepareBook());
        Assert.assertNotNull(book);
    }

    @Test
    public void test_updateStock() {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(prepareBook()));
        Mockito.when(bookRepository.save(prepareUpdateBook())).thenReturn(prepareUpdateBook());
        final Book book = bookService.updateStock(
                1L,12);
        Assert.assertNotNull(book);
    }

    private Book prepareBook() {
        final Book book = new Book();
        book.setCount(124);
        book.setId(1L);
        book.setAuthor("Testets");
        book.setName("Test");
        book.setPrice(19.0);
        return book;
    }

    private Book prepareUpdateBook() {
        final Book book = new Book();
        book.setCount(12);
        book.setId(1L);
        book.setAuthor("Testets");
        book.setName("Test");
        book.setPrice(19.0);
        return book;
    }

}
