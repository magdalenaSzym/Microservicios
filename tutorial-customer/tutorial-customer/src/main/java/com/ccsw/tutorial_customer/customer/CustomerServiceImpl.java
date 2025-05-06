package com.ccsw.tutorial_customer.customer;

import com.ccsw.tutorial_customer.customer.model.Customer;
import com.ccsw.tutorial_customer.customer.model.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * {@inheritDoc}
     * @param id PK de la entidad
     * @return
     */
    @Override
    public Customer get(Long id) {

        return this.customerRepository.findById(id).orElse(null);
    }

    /**
     *{@inheritDoc}
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.customerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, CustomerDto dto) throws Exception {

        Customer customer;

        if (customerRepository.existsByName(dto.getName())) {
            throw new Exception("Customer name already exists");
        }
        if (id == null) {

            customer = new Customer();
        } else {
            customer = this.get(id);
        }

        customer.setName(dto.getName());

        this.customerRepository.save(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exist");
        }

        this.customerRepository.deleteById(id);
    }
}
