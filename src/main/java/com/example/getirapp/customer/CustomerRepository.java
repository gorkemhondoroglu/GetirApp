package com.example.getirapp.customer;

import com.example.getirapp.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Customer findByMail(String mail);

}
