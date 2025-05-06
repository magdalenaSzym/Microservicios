package com.ccsw.tutorial_customer.customer;

import com.ccsw.tutorial_customer.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    boolean existsByName(String name);
}
