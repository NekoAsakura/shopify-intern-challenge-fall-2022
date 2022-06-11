package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
