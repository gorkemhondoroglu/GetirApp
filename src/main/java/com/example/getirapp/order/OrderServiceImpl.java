package com.example.getirapp.order;

import com.example.getirapp.book.BookServiceImpl;
import com.example.getirapp.common.exception.ResourceNotFoundException;
import com.example.getirapp.common.exception.UnknownErrorException;
import com.example.getirapp.common.service.SequenceGeneratorService;
import com.example.getirapp.customer.CustomerServiceImpl;
import com.example.getirapp.model.entity.Customer;
import com.example.getirapp.model.entity.Order;
import com.example.getirapp.model.entity.OrderBook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private CustomerServiceImpl customerService;

    private ReentrantLock reentrantlock = new ReentrantLock();

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Order getOrderByOrderId(Long orderId) throws ResourceNotFoundException {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
        return order;

    }

    @Override
    public List<Order> getByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orderList = orderRepository.findAllByCreateDateBetween(startDate, endDate);
        if(CollectionUtils.isEmpty(orderList)) {
            throw new ResourceNotFoundException("Order not found.");
        }
        return orderList;
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {

        final List<OrderBook> orderBookList = order.getBooks();
        reentrantlock.lock();
        List<OrderBook> bookList = bookService.getAvailableBooks(order);
        if (orderBookList.size() == 0) {
            reentrantlock.unlock();
            return null;
        }
        final Customer customer = customerService.getCustomerById((order.getCustomer().getId()));

        order.setCreateDate(LocalDate.now());
        order.setCustomer(customer);
        order.setBooks(orderBookList);
        Double totalPrice = calculateTotalPrice(bookList);
        order.setTotalPrice(totalPrice);
        order.setId(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));

        final Order newOrder = orderRepository.save(order);
        if (newOrder == null || newOrder.getId() == null) {
            throw new UnknownErrorException("An unknown error occurred while creating order.");
        }

        reentrantlock.unlock();

        return newOrder;

    }

    @Override
    public List<Order> getAllOrdersByCustomerId(Long customerId) {
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        if(CollectionUtils.isEmpty(orderList)) {
            throw new ResourceNotFoundException("Order not found. Customer id: " + customerId);
        }
        return orderList;
    }

    private Double calculateTotalPrice(List<OrderBook> bookList){
        Double totalPrice = Double.valueOf(0);
        for (OrderBook orderedBook : bookList) {
            totalPrice += orderedBook.getCount() * orderedBook.getBook().getPrice();
        }
        return totalPrice;
    }

}
