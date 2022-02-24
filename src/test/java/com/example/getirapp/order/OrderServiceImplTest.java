package com.example.getirapp.order;


import com.example.getirapp.book.BookServiceImpl;
import com.example.getirapp.common.service.SequenceGeneratorService;
import com.example.getirapp.customer.CustomerServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.locks.ReentrantLock;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookServiceImpl bookService;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private ReentrantLock reentrantlock;


    @Before
    public void setup() {

    }
}
