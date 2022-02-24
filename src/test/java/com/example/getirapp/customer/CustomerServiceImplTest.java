package com.example.getirapp.customer;

import com.example.getirapp.common.service.SequenceGeneratorService;
import com.example.getirapp.model.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private CustomerService customerService;


    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Before
    public void setup() {
        customerService = new CustomerServiceImpl(customerRepository,sequenceGeneratorService);
        Mockito.when(customerRepository.findByMail("Test@test.com")).thenReturn(null);
        Mockito.when(sequenceGeneratorService.generateSequence("customer_sequence")).thenReturn(1L);

    }

    @Test
    public void test_add() {
        Mockito.when(customerRepository.save(prepareCustomer())).thenReturn(prepareCustomer());
        final Customer customer = customerService.add(
                prepareCustomer());
        Assert.assertNotNull(customer);
    }


    private Customer prepareCustomer() {
        final Customer customer = new Customer();
        customer.setId(1L);
        customer.setSurname("Testets");
        customer.setName("Test");
        customer.setMail("Test@test.com");
        return customer;
    }
}
