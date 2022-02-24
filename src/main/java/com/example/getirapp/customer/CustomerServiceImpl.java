package com.example.getirapp.customer;


import com.example.getirapp.common.service.SequenceGeneratorService;
import com.example.getirapp.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    @Transactional
    public Customer add(Customer customerReq) {
        Customer customer = customerRepository.findByMail(customerReq.getMail());
        customerReq.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
        if(!Objects.isNull(customer)) {
            throw new IllegalArgumentException("There is an already existed user with the same email.");
        }
        return customerRepository.save(customerReq);
    }

    public Customer getCustomerById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()){
            throw new IllegalArgumentException("There is no customer with the given id");
        }
        return customer.get();
    }
}
